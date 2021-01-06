package com.chenyuhu.test;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

/**
 * @author cyh
 * @date 2020/12/28 11:59
 */
public class TestCreate {
    /**
     * 使用activiti 提供的默认方式来创建mysql表
     *
     */
    @Test
    public  void  testCreateDbTable(){
     //需要调用activiti 提供的工具类  ProcessEngine
        // getDefaultProcessEngine会从resources下获取activiti.cfg.xml的文件
       // ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //自定义创建processEngines
        ProcessEngineConfiguration engineConfiguration =
                ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml","processEngineConfiguration");
        ProcessEngine processEngine = engineConfiguration.buildProcessEngine();
        HistoryService historyService = processEngine.getHistoryService();

        System.out.println(processEngine);

    }

}
