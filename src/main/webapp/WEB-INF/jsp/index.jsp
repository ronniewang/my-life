<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>时间都去哪了</title>
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/angular.js/1.4.3/angular.min.js"></script>
    <script src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="//cdn.bootcss.com/angular-ui-bootstrap/0.13.2/ui-bootstrap.min.js"></script>
    <script src="//cdn.bootcss.com/angular-ui-bootstrap/0.13.2/ui-bootstrap-tpls.min.js"></script>
    <link href="//cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker3.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%= request.getServletContext().getContextPath()%>/css/style.css"/>
    <script src="<%= request.getServletContext().getContextPath()%>/js/bootstrap-datepicker.js"></script>
    <style>
        .chosen {
            background-color: gold;
        }

        body {
            padding-top: 70px;
            margin: 0 auto;
            width: 80%;
        }

        .form-center {
            text-align: center;
            padding-top: 10px;
        }

        .first-td {
            width: 10%
        }
    </style>
</head>

<body ng-app="eventsApp" class="container-fluid" ng-controller="eventsCtrl">
<div class="navbar navbar-default navbar-fixed-top">
    <div class="form-inline form-center">
        <div class="form-group">
            <input type="text" class="form-control" ng-model="query" placeholder="Search">
            <button type="button" class="btn btn-primary" ng-click="selectAll()">Select All</button>
            <label for="startDate">Start Date</label>
            <input class="datepicker" type="text" data-date-format="mm/dd/yyyy" readonly="true" id="startDate">
            <label for="endDate">End Date</label>
            <input class="datepicker" type="text" data-date-format="mm/dd/yyyy" readonly="true" id="endDate">
            <button type="button" class="btn btn-success" ng-click="refresh()" id="refreshBtn">Refresh</button>
        </div>
    </div>
</div>
<%--<div class="col-md-2">--%>
    <%--<jsp:include page="navigation.jsp"/>--%>
<%--</div>--%>
<div class="table-responsive">
    <ul class="list-group">
        <li class="list-group-item" ng-repeat="event in eventsList | filter:filterMatchCode | orderBy:eventStartTime">
            <table class="table table-hover text-left">
                <thead>
                <tr>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>事件描述</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td class="first-td">{{event.eventStartTime}}</td>
                    <td class="first-td">{{event.eventEndTime}}</td>
                    <td>{{event.eventDescription}}</td>
                </tr>
                </tbody>
            </table>
        </li>
    </ul>
</div>
</body>

</html>
<script src="<%= request.getServletContext().getContextPath()%>/js/my-angular-config.js"></script>
<script>
    $(function () {
        $('.datepicker').datepicker({
            autoclose: true
        });
    });
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
        $scope.selectAll = function () {
            $.each($scope.eventsList, function (i, value) {
                value.choose = true;
            });
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