<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>时间都去哪了</title>
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/angular.js/1.4.3/angular.min.js"></script>
    <script src="//cdn.bootcss.com/angular-resource/1.5.0/angular-resource.min.js"></script>
    <script src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <script src="//cdn.bootcss.com/angular-ui-bootstrap/0.13.2/ui-bootstrap.min.js"></script>
    <script src="//cdn.bootcss.com/angular-ui-bootstrap/0.13.2/ui-bootstrap-tpls.min.js"></script>
    <link href="//cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker3.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%= request.getServletContext().getContextPath()%>/css/style.css"/>
    <script src="<%= request.getServletContext().getContextPath()%>/js/bootstrap-datepicker.js"></script>
    <style>
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

        .time-line-cell {
            display: table-cell;
            line-height: 8px;
            text-indent: -9999px;
        }
    </style>
</head>

<body ng-app="eventsApp" class="container-fluid" ng-controller="eventsCtrl">
<%--<div class="navbar navbar-default navbar-fixed-top">--%>
<%--<div class="form-inline form-center">--%>
<%--<div class="form-group">--%>
<%--<input type="text" class="form-control" ng-model="query" placeholder="Search">--%>
<%--<button type="button" class="btn btn-primary" ng-click="selectAll()">Select All</button>--%>
<%--<button type="button" class="btn btn-success" ng-click="refresh()" id="refreshBtn">Refresh</button>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="col-md-2">--%>
<%--<jsp:include page="navigation.jsp"/>--%>
<%--</div>--%>
<div class="table-responsive">
    <form class="form-inline">
        <label for="startDate">Start Date</label>
        <input class="datepicker" type="text" data-date-format="mm/dd/yyyy" readonly="true" id="startDate">

        <div class="form-group">
            <label>结束时间: </label>
            <select class="form-control" ng-model="hour" ng-options="h for h in hours">
                <option value="">请选择小时</option>
            </select>
            <label>时</label>
        </div>
        <div class="form-group">
            <select class="form-control" ng-model="minute" ng-options="min for min in minutes">
                <option value="">请选择分钟</option>
            </select>
            <label>分</label>
        </div>
        <div class="form-group">
            <label>事情类型: </label>
            <select class="form-control" ng-model="currentParentType" ng-change="updateChildren()"
                    ng-options="item.typeDescription for item in eventTypes | filter:filterParentType track by item.typeId">
            </select>
        </div>
        <%--<div class="form-group">--%>
        <%--<label>子类型</label>--%>
        <%--<select class="form-control" ng-model="currentParentType" ng-options="item.typeDescription for item in eventChildTypes track by item.typeId">--%>
        <%--</select>--%>
        <%--</div>--%>
        <button type="submit" class="btn btn-default" ng-click="submit()">Submit</button>
    </form>
    <div class="form-group">
        <label>描述</label>
        <textarea class="form-control" rows="3" ng-model="desc" type="textarea"></textarea>
    </div>
    <div class="form-group">
        <label>相关资源</label>
        <textarea class="form-control" rows="1" ng-model="eventResource" type="textarea"></textarea>
    </div>
</div>
<div style="width: 100%; display: table;">
    <span class="time-line-cell" ng-repeat="config in timeLineCellConfigs"
          style="width: {{config.width}}; background-color:{{config.bgcolor}};">JavaScript</span>
</div>
<div class="table-responsive">
    <ul class="list-group">
        <li class="list-group-item" ng-repeat="event in eventList | filter:filterMatchCode | orderBy:eventStartTime">
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
    var app = angular.module("eventsApp", ['ngResource']);
    MY_ANGULAR_CONFIG(app);
    app.controller("eventsCtrl", function ($scope, $http, $resource) {
        $scope.currentParentType;
        $scope.eventList = [];
        $scope.eventTypes = [];
        $scope.eventChildTypes = [];
        $scope.hour;
        $scope.hours = [];
        for (var i = 0; i < 24; i++) {
            $scope.hours.push(i);
        }
        ;
        $scope.minute;
        $scope.minutes = [];
        for (var i = 0; i < 60; i++) {
            $scope.minutes.push(i);
        }
        ;
        $scope.timeLineCellConfigs = [{
            "width": "30%",
            "bgcolor": "#f1e05a"
        }, {
            "width": "40%",
            "bgcolor": "#b07219"
        }, {
            "width": "30%",
            "bgcolor": "#f1e05a"
        }];
        $scope.startDate = null;
        $scope.endDate = null;
        $http.get("events/?s=eventStartTime,desc").success(function (res) {
            $scope.eventList = res._embedded.events;
        });
        $http.get("eventTypes").success(function (res) {
            $scope.eventTypes = res._embedded.eventTypes;
            $scope.currentParentType = $scope.eventTypes[0];
            $scope.updateChildren();
        });
        $scope.saveUser = function () {
            var User = $resource('events');
            User.save({
                "eventStartTime": $scope.eventList[0].eventStartTime,
                "eventEndTime": new Date,
                "eventDescription": 'test',
                "eventResources": null,
                "eventType": 0
            });
        }
        $scope.filterParentType = function (element) {
            if (element.parentTypeId == 0) {
                return true;
            }
            return false;
        };
        $scope.updateChildren = function () {
            $scope.eventChildTypes = [];
            angular.forEach($scope.eventTypes, function (value, key) {
                if (value.parentTypeId == $scope.currentParentType.typeId) {
                    $scope.eventChildTypes.push(value);
                    alert(value)
                }
            });
        };
        $scope.desc;
        $scope.eventResource;
        $scope.submit = function () {
            if (!$scope.desc) {
                alert("desc null");
            } else {
//                alert($scope.desc);
            }
            var event = {
                "eventStartTime": $scope.eventList[0].eventEndTime + ":00",
                "eventEndTime": $scope.eventList[0].eventEndTime.substr(0, 10) + " " + $scope.paddingZero($scope.hour) + ":" + $scope.paddingZero($scope.minute) + ":00",
                "eventDescription": $scope.desc,
                "eventResources": $scope.eventResource,
                "eventType": 0
            };
//            var req = {
//                method: 'POST',
//                url: 'add',
//                headers: {
//                    'Content-Type': 'application/json; charset=utf-8'
//                },
//                data: {"entity":event}
//            };
            $http.post("add", {
                "entity": JSON.stringify(event)
            }).success(function (res) {
                if (res.errorCode != 0) {
                    alert("添加失败" + res.errorMessage);
                } else {
                    alert("success");
                }
            }).error(function (res) {
                alert("error");
            })
//            $scope.saveUser();
        };
        $scope.paddingZero = function (i) {
            if (i < 10) {
                return "0" + i;
            }
            return i;
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