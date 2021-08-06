package com.example.taskdemo.business;


import com.example.taskdemo.bean.ResponseResult;
import com.example.taskdemo.entity.EaEhomeDk;
import com.example.taskdemo.mapper.EaEhomeDkMapper;
import com.example.taskdemo.util.FixedThreadUtil;
import com.example.taskdemo.util.GsonUtil;
import com.example.taskdemo.util.HttpConnectionPoolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;


@Component
public class Task {

    private static final String END_POINT = "http://223.240.111.154:8082/api/ehome/yzdk.php?token=131425";

    @Autowired
    private EaEhomeDkMapper eaEhomeDkMapper;

    //    @Scheduled(fixedDelay = 1000000000)
    public void func1() {
        System.out.println("----任务开始----");
        long sTime = System.currentTimeMillis();

        String time = "08:02";
        Example example = new Example(EaEhomeDk.class);
        example.createCriteria().andEqualTo("randomtime1", time);
        List<EaEhomeDk> list = eaEhomeDkMapper.selectByExample(example);
        System.out.println("需要处理的数据条数：" + list.size());

        for (EaEhomeDk item : list) {
//            this.singleHandler(item, null);
        }

        long eTime = System.currentTimeMillis();
        System.out.println("----任务结束，累计耗时----" + (eTime - sTime) / 1000D);
    }

    @Scheduled(fixedDelay = 60000)
    @Async("executor")
    public void func2() throws InterruptedException {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("HH:mm");
        String currentTimeStr = format.format(date); //08:01的格式
//        String currentTimeStr = "08:02";
        System.out.println(currentTimeStr + "----任务开始----");
        long sTime = System.currentTimeMillis();

        Example example = new Example(EaEhomeDk.class);
        example.createCriteria().andEqualTo("randomtime1", currentTimeStr);
        List<EaEhomeDk> list = eaEhomeDkMapper.selectByExample(example);
        System.out.println(currentTimeStr + "需要处理的数据条数：" + list.size());

        if (CollectionUtils.isEmpty(list)) {
            System.out.println(currentTimeStr + "----任务结束，没有需要处理的数据----");
            return;
        }

        final CountDownLatch countDownLatch = new CountDownLatch(list.size());

        //多线程处理
        for (EaEhomeDk item : list) {
            FixedThreadUtil.executeTask(new Runnable() {
                @Override
                public void run() {
                    singleHandler(item, currentTimeStr, countDownLatch);
                }
            });
        }
        countDownLatch.await();
        long eTime = System.currentTimeMillis();
        System.out.println(currentTimeStr + "----任务结束，累计耗时----" + (eTime - sTime) / 1000D);
    }

    private void singleHandler(EaEhomeDk item, String currentTimeStr, CountDownLatch countDownLatch) {
        if (StringUtils.isEmpty(item.getSalesNo())) {
            countDownLatch.countDown();
            return;
        }

        long startTime = System.currentTimeMillis();

        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("cmd", "main");
        dataMap.put("check", "0");
        dataMap.put("username", item.getSalesNo());
        dataMap.put("password", item.getPassword());
        String paramsJson = GsonUtil.beanToJson(dataMap);
        String responseString;
        try {
            responseString = HttpConnectionPoolUtils.post(END_POINT, paramsJson, null);
        } catch (IOException e) {
            System.out.println(currentTimeStr + "请求抛出异常：" + e.getMessage());
            return;
        }

        ResponseResult responseResult = GsonUtil.jsonToBean(responseString, ResponseResult.class);
        if (responseResult == null) {
            System.out.println(currentTimeStr + "请求成功后json转bean出错");
            return;
        }

        if ("true".equals(responseResult.getStatus())) {
            EaEhomeDk updateItem = new EaEhomeDk();
            updateItem.setId(item.getId());
            updateItem.setExectime1((byte) 1);
            updateItem.setExec_results(responseResult.getMsg());
            updateItem.setRandomtime1(currentTimeStr + "-");
            eaEhomeDkMapper.updateByPrimaryKeySelective(updateItem);
        } else {
            EaEhomeDk updateItem = new EaEhomeDk();
            updateItem.setId(item.getId());
            updateItem.setExectime1((byte) 3);
            updateItem.setExec_results(responseResult.getMsg());
            eaEhomeDkMapper.updateByPrimaryKeySelective(updateItem);
        }

        long endTime = System.currentTimeMillis();
        String costTime = " 耗时" + (endTime - startTime) / 1000D;
        System.out.println(currentTimeStr + " " + item.getSalesNo() + costTime + " 请求结果：" + responseString);
        countDownLatch.countDown();
    }

}
