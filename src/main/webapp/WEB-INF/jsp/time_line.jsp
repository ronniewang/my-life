<%--
  Created by IntelliJ IDEA.
  User: ronnie
  Date: 2016/4/22
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html ng-app="eventsApp">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>演示：HTML5+CSS3实现的响应式垂直时间轴</title>
    <script src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/angular.js/1.4.3/angular.min.js"></script>
    <link rel="stylesheet" href="http://www.helloweba.com/demo/css/main.css"/>
    <link rel="stylesheet" href="<%= request.getServletContext().getContextPath()%>/css/style.css"/>
    <style type="text/css">
        h2.top_title {
            border-bottom: none;
            background: none;
            text-align: center;
            line-height: 32px;
            font-size: 20px
        }

        h2.top_title span {
            font-size: 12px;
            color: #666;
            font-weight: 500
        }
    </style>
</head>

<body ng-controller="eventsCtrl">
<div id="header">
    <div id="logo">
        <h1>
            <a href="<%= request.getServletContext().getContextPath()%>/index" title="返回helloweba首页">回首页</a>
        </h1>
    </div>
</div>

<h2 class="top_title"><a href="http://www.helloweba.com/view-blog-285.html">HTML5+CSS3实现的响应式垂直时间轴</a><br/><span>请使用IE9+或Chrome，Firefox高级浏览器或手机访问本页</span>
</h2>

<section id="cd-timeline" class="cd-container">
    <div class="cd-timeline-block" ng-repeat="event in eventsList | filter:filterMatchCode | orderBy:eventStartTime">
        <div class="cd-timeline-img cd-picture">
            <img src="<%= request.getServletContext().getContextPath()%>/img/cd-icon-picture.svg" alt="Picture">
        </div>

        <div class="cd-timeline-content">
            <h2>{{event.eventStartTime}}</h2>
            <p>{{event.eventDescription}}</p>
            <a href="{{event.eventResources}}" class="cd-read-more" target="_blank">相关资源</a>
            <span class="cd-date">{{event.eventEndTime}}</span>
        </div>
    </div>
</section>

<div id="footer">
    <p>Powered by helloweba.com 允许转载、修改和使用本站的DEMO，但请注明出处：<a href="http://www.helloweba.com">www.helloweba.com</a></p>
</div>
</body>
</html>
<script src="<%= request.getServletContext().getContextPath()%>/js/my-angular-config.js"></script>
<script>
    var app = angular.module("eventsApp", []);
    MY_ANGULAR_CONFIG(app);
    app.controller("eventsCtrl", function ($scope, $http) {
        $scope.eventsList = [];
        $scope.startDate = null;
        $scope.endDate = null;
        $http.get("events").success(function (res) {
            $scope.eventsList = res._embedded.events;
        });
        $scope.filterMatchCode = function (element) {
            if ($.trim($scope.query) == '') {
                return true;
            }
            return element.matchCode.indexOf($scope.query) != -1 ? true : false;
        }
        $scope.chooseOdds = function (event) {
            if (event.choose) {
                event.choose = false;
            } else {
                event.choose = true;
            }
        }
        $scope.validate = function (start, end) {
            if (start == "") {
                alert("start date can not be null");
                return false;
            }
            if (end == "") {
                alert("end date can not be null");
                return false;
            }
            if (end.substring(6, 10) - start.substring(6, 10) < 0) {
                alert("end date should be greater than start date");
                return false;
            }
            if (end.substring(6, 10) - start.substring(6, 10) == 0 && end.substring(0, 2) - start.substring(0, 2) < 0) {
                alert("end date should be greater than start date");
                return false;
            }
            if (end.substring(6, 10) - start.substring(6, 10) == 0 && end.substring(0, 2) - start.substring(0, 2) == 0 && end.substring(3, 5) - start.substring(3, 5) < 0) {
                alert("end date should be greater than start date");
                return false;
            }
            return true;
        }
    });
</script>