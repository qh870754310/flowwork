package com.qh.modules.activiti.config;

import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * activiti工作流配置
 * Activiti与spring整合xml配置文件
 * Activiti的数据库名称都以ACT_开头。第二部分是表的用例的双字符标识。此用例也将大致匹配服务API
 * ACT_RE_ *：RE代表存储库。具有此前缀的表包含静态信息，例如流程定义和流程资源（图像，规则等）。
 * ACT_RU_ *：RU代表运行时。这些是包含流程实例，用户任务，变量，作业等的运行时数据的运行时表.Activiti仅在流程实例执行期间存储运行时数据，并在流程实例结束时删除记录。这使运行时表保持小而快
 * ACT_ID_ *：ID代表身份。这些表包含身份信息，例如用户，组等。
 * ACT_HI_ *：HI代表历史。这些是包含历史数据的表，例如过去的流程实例，变量，任务等。
 * ACT_GE_ *：一般数据，用于各种用例。
 *
 * Created by Administrator on 2018/4/24.
 */

@Configuration
public class Cfg_Activiti {

    /**
     *  工作流引擎配置
     * 流程配置，与Spring整合采用SpringProcessEngineConfiguration这个实现
     * @param dataSource
     * @param transactionManager
     * @return
     */
    @Bean
    public ProcessEngineConfiguration processEngineConfiguration(DataSource dataSource, PlatformTransactionManager transactionManager) {
        SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
        processEngineConfiguration.setDataSource(dataSource);
        processEngineConfiguration.setDatabaseSchemaUpdate("true");
        processEngineConfiguration.setDatabaseType("mysql");
        processEngineConfiguration.setJobExecutorActivate(false);
        processEngineConfiguration.setTransactionManager(transactionManager);

        //流程图字体
        processEngineConfiguration.setActivityFontName("宋体");
        processEngineConfiguration.setAnnotationFontName("宋体");
        processEngineConfiguration.setLabelFontName("宋体");

        return processEngineConfiguration;
    }

    /**
     * 工作流引擎
     *
     * 流程引擎，与spring整合使用factoryBean
     *
     * @param processEngineConfiguration
     * @return
     */
    @Bean
    public ProcessEngineFactoryBean processEngineFactoryBean(ProcessEngineConfiguration processEngineConfiguration) {
        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
        return processEngineFactoryBean;
    }

    //八大接口(原生服务)
    @Bean
    public RepositoryService repositoryService(ProcessEngine processEngine) {
        return processEngine.getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService(ProcessEngine processEngine) {
        return processEngine.getRuntimeService();
    }

    @Bean
    public TaskService taskService(ProcessEngine processEngine) {
        return processEngine.getTaskService();
    }

    @Bean
    public HistoryService historyService(ProcessEngine processEngine) {
        return processEngine.getHistoryService();
    }

    @Bean
    public FormService formService(ProcessEngine processEngine) {
        return processEngine.getFormService();
    }

    @Bean
    public IdentityService identityService(ProcessEngine processEngine) {
        return processEngine.getIdentityService();
    }

    @Bean
    public ManagementService managementService(ProcessEngine processEngine) {
        return processEngine.getManagementService();
    }

    @Bean
    public DynamicBpmnService dynamicBpmnService(ProcessEngine processEngine) {
        return processEngine.getDynamicBpmnService();
    }

    //八大接口 end
}
