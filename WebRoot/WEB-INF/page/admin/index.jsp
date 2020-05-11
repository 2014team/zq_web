<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>后台中心</title>	
	<%@include file="/WEB-INF/page/admin/common/head_layui.jsp" %>
	<%@ taglib uri="/WEB-INF/tag/right.tld" prefix="r" %>  
	
  </head>
  
  <body>
    <!-- 顶部开始 -->
    <div class="container">
        <div class="logo"><a href="index.html">L-admin v2.0</a></div>
        <div class="left_open">
            <i title="展开左侧栏" class="iconfont" style="font-size: 20px;">&#xe668;</i>
        </div>
        
        <ul class="layui-nav right">
          <li class="layui-nav-item">
            <a href="javascript:;" style="height: 52px">admin</a>
            <dl class="layui-nav-child"> 
              <dd><a href="/admin/logout">退出</a></dd>
            </dl>
          </li>
          <li class="layui-nav-item to-index"><a href="#" style="height: 52px">前台首页</a></li>
        </ul>
        
    </div>
    <!-- 顶部结束 -->
    <!-- 中部开始 -->
     <!-- 左侧菜单开始 -->
    <div class="left-nav">
      <div id="side-nav">
        <ul id="nav">
             <li >
                <a href="javascript:;">
                    <i class="iconfont" style="font-size: 20px;">&#xe68e;</i>
                    <cite>主页</cite>
                    <i class="iconfont nav_right">&#xe602;</i>
                </a>
                <ul class="sub-menu">
                    <li><a _href="html/welcome.html"><i class="iconfont">&#xe602;</i><cite>控制台</cite></a></li >
                </ul>
            </li>

             <r:auth menuName="管理员管理" menuUrl="">
	            <li>
	            
	                <a href="javascript:;">
	                    <i class="iconfont" style="font-size: 20px;">&#xe770;</i>
	                    <cite>管理员管理</cite>
	                    <i class="iconfont nav_right">&#xe602;</i>
	                </a>
	                <ul class="sub-menu">
	                    <li>
	                        <a _href="/admin/user/list/ui">
	                            <i class="iconfont">&#xe602;</i>
	                            <cite>管理员列表</cite>
	                        </a>
	                    </li >
	                    <li>
	                        <a _href="/admin/role/list/ui">
	                            <i class="iconfont">&#xe602;</i>
	                            <cite>角色管理</cite>
	                        </a>
	                    </li >
	                    <li>
	                        <a _href="/admin/menu/list/ui">
	                            <i class="iconfont">&#xe602;</i>
	                            <cite>菜单管理</cite>
	                        </a>
	                    </li >
	                </ul>
	            </li>  
            
            </r:auth>
                      
                
                </ul>
            </li> 
            </ul>
      </div>
    </div>
    <!-- <div class="x-slide_left"></div> -->
    <!-- 左侧菜单结束 -->
    <!-- 右侧主体开始 -->
    <div class="page-content">
        <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
          <ul class="layui-tab-title">
            <li class="home"><i class="layui-icon">&#xe68e;</i>我的桌面</li>
          </ul>
          <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='/admin/role/list/ui' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
          </div>
        </div>
    </div>
    <div class="page-content-bg"></div>
    <!-- 右侧主体结束 -->
    <!-- 中部结束 -->
    <!-- 底部开始 -->
    <!--<div class="footer">
        <div class="copyright">Copyright ©2019 L-admin v2.3 All Rights Reserved</div>  
    </div>-->
    <!-- 底部结束 -->
</body>
</html>