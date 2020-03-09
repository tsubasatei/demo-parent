package com.xt.servlet;

import com.google.gson.Gson;
import com.xt.entity.User;
import com.xt.service.UserService;
import com.xt.service.impl.UserServiceImpl;
import com.xt.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * 用户登录、注册
 */
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    // 处理登录的功能
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 调用userService.login()登录处理业务
        User loginUser = null;
        try {
            loginUser = userService.login(new User(null, username, password, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 如果等于null,说明登录失败!
        if (loginUser == null) {
            // 把错误信息，和回显的表单项信息，保存到Request 域中
            req.setAttribute("msg", "用户名或密码错误");
            req.setAttribute("username", username);
            // 跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            // 登录成功
            req.getSession().setAttribute("loginUser", loginUser);
            // 跳到成功页面login_success.jsp
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    // 处理注册的功能
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取Session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        // 1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        User user = WebUtils.copyParam2Bean(new User(), req.getParameterMap());

        // 2、检查验证码是否正确=== 写死,要求验证码为:abcde
        if (code != null && code.equals(token)) {
            // 3、检查用户名是否可用
            try {
                if (userService.existsUsername(username)) {
                    System.out.println("用户名[" + username + "]已存在!");
                    // 把回显信息，保存到Request 域中
                    req.setAttribute("msg", "用户名已存在");
                    req.setAttribute("username", username);
                    req.setAttribute("email", email);

                    // 跳回注册页面
                    req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
                } else {
                    // 可用 调用 Service 保存到数据库
                    userService.registerUser(user);
                    // 跳到注册成功页面regist_success.jsp
                    req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // 把回显信息，保存到Request 域中
            req.setAttribute("msg", "验证码错误");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/register.jsp").forward(req, resp);
        }
    }

    // 登出
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、销毁Session 中用户登录的信息（或者销毁Session）
        req.getSession().invalidate();
        // 2、重定向到首页（或登录页面）。
        resp.sendRedirect(req.getContextPath());
    }

    // 验证用户名是否可用
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String username = req.getParameter("username");
        boolean existsUsername = userService.existsUsername(username);
        // 把返回的结果封装成为map 对象
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername", existsUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }


}
