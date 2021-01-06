package com.chenyuhu.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

/**
 * @author cyh
 * @date 2020/12/28 15:26
 */
public class ActivitiDemo {

    @Test
    public void testDeployment(){
    //创建processEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取repositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 使用repositoryService 进行流程的部署，定义一个流程的名字，把生成好的bpmn和png部署到数据中
        Deployment deploy = repositoryService.createDeployment()
                .name("出差申请")
                .disableSchemaValidation()
                .addClasspathResource("bpmn/evection.bpmn")
                .addClasspathResource("bpmn/evection.png")
                .deploy();
        //输出部署
        System.out.println(deploy.getId());
        System.out.println(deploy.getName());

    }
}
