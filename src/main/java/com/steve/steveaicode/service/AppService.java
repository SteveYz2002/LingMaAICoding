package com.steve.steveaicode.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.steve.steveaicode.model.dto.app.AppQueryRequest;
import com.steve.steveaicode.model.entity.App;
import com.steve.steveaicode.model.entity.User;
import com.steve.steveaicode.model.vo.AppVO;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * 应用 服务层。
 *
 * @author <a>Steve.Yz</a>
 */
public interface AppService extends IService<App> {

    /**
     * 通过对话生成应用代码
     *
     * @param appId     应用 id
     * @param message   提示词
     * @param loginUser 登录用户
     * @return 应用代码
     */
    Flux<String> chatToGenCode(Long appId, String message, User loginUser);

    /**
     * 应用部署
     *
     * @param appId     应用 id
     * @param loginUser 登录用户
     * @return 可访问的应用部署地址
     */
    String  deployApp(Long appId, User loginUser);

    /**
     * 获取应用封装类
     *
     * @param app 应用
     * @return 应用封装类
     */
    AppVO getAppVO(App app);

    /**
     * 获取应用封装类列表
     *
     * @param appList 应用列表
     * @return 应用封装类列表
     */
    List<AppVO> getAppVOList(List<App> appList);

    /**
     * 根据查询条件查询应用封装类列表
     *
     * @param appQueryRequest 查询请求
     * @return 应用封装类列表
     */
    QueryWrapper getQueryWrapper(AppQueryRequest appQueryRequest);


}
