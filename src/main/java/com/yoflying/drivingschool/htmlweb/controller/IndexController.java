package com.yoflying.drivingschool.htmlweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.yoflying.drivingschool.constdef.ErrorDef;
import com.yoflying.drivingschool.domain.dao.LoveMapper;
import com.yoflying.drivingschool.domain.dao.TestDataMapper;
import com.yoflying.drivingschool.domain.jpa.TestData;
import com.yoflying.drivingschool.domain.model.CoachTestaAddress;
import com.yoflying.drivingschool.entity.LoveEntity;
import com.yoflying.drivingschool.utils.json.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by arvin on 2016/12/22.
 */
@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/index")
    public String index(HttpServletRequest request, ModelMap modelMap) {
        modelMap.put("index","欢迎使用 柚飞科技约车系统 ！开发人员 姚九龙 李娇 李立强");
        
        String ua = request.getHeader("user-agent");
        //// TODO: 17/1/12 判断是否为手机用户

        return "/index/index.ftl";
    }

    @Resource
    LoveMapper loveMapper;

    @RequestMapping("/love")
    public String love(String uuid, ModelMap modelMap) {

        if (StringUtils.isEmpty(uuid)) {
            return "/love.ftl";
        }

        LoveEntity loveEntity = loveMapper.findLove(uuid);

        if (loveEntity == null) {
            return "/love.ftl";
        }

        modelMap.put("love", loveEntity);

        return "/love2.ftl";
    }

    @RequestMapping(value = "/lovePost", method = RequestMethod.POST)
    public String lovePost(LoveEntity love, ModelMap modelMap) {

        String uuid = UUID.randomUUID().toString();
        love.setUuid(uuid);

        if(StringUtils.isEmpty(love.getMe()) || StringUtils.isEmpty(love.getYouname()) || StringUtils.isEmpty(love.getLovetitle()) || StringUtils.isEmpty(love.getShowmian()) ||
                StringUtils.isEmpty(love.getShowmian2()) || StringUtils.isEmpty(love.getShowmian3()) || StringUtils.isEmpty(love.getTime())) {
            modelMap.put("ret", -1);
            modelMap.put("uuid", "");
            return "/love3.ftl";
        }

        int ret = loveMapper.insertLove(love);

        modelMap.put("ret", -1);

        if (ret > 0) {
            modelMap.put("ret", 0);
        }

        modelMap.put("uuid", uuid);


        return "/love3.ftl";
    }


    @Autowired
    TestDataMapper testDataMapper;
    @RequestMapping(value = "/testData", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult testData(TestData data) {

        int ret = testDataMapper.insert(data);

        return ret > 0 ? new JsonResult<CoachTestaAddress> ("操作成功", ErrorDef.SUCCESS)
                :  new JsonResult<CoachTestaAddress> ("操作失败", ErrorDef.SUCCESS);
    }

    @RequestMapping("/exception")
    public String exception(ModelMap modelMap) {
        modelMap.put("error","错了错啦");
        return "/exception.ftl";
    }

    @RequestMapping("/404")
    public String err404(ModelMap modelMap) {

        return "/index/404.ftl";
    }
}
