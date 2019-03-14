package com.ruoyi.web.controller.tool;

import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.framework.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger测试
 */
@Api("用户管理信息")
@RestController
@RequestMapping("/test/*")
public class SwaggerTestController extends BaseController{
   private static final List<Test> testList = new ArrayList<>();
    {
        testList.add(new Test("1","张三","12346"));
        testList.add(new Test("2","李四","123467"));
    }
    @ApiOperation("列表展示")
    @GetMapping("list")
    public List<Test> testList(){
        return testList;
    }
    @ApiOperation("添加")
    @PostMapping("add")
    public AjaxResult add(Test test){
        return testList.add(test)?success():error();
    }
    @ApiOperation("修改")
    @ApiImplicitParam(name="test",value = "单个用户",dataType = "Test")
    @PutMapping("update")
    public AjaxResult update(Test test){
        return testList.remove(test)&&testList.add(test)?success():error();
    }
    @ApiOperation("删除")
    @ApiImplicitParam(name ="tests" ,value="单个用户",dataType = "Test")
    @DeleteMapping("delete")
    public AjaxResult delete(Test test){
        return testList.remove(test)?success():error();
    }

    class Test{
        private String userId;
        private String username;
        private String password;



        public Test(String userId, String username, String password) {
            this.userId = userId;
            this.username = username;
            this.password = password;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Test test = (Test) o;

            if (!userId.equals(test.userId)) return false;
            if (!username.equals(test.username)) return false;
            return password.equals(test.password);
        }

        @Override
        public int hashCode() {
            int result = userId.hashCode();
            result = 31 * result + username.hashCode();
            result = 31 * result + password.hashCode();
            return result;
        }
    }
}
