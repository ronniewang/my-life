<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>时间都去哪了</title>
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker3.min.css" rel="stylesheet">
    <link href="<%=request.getServletContext().getContextPath()%>/css/custom.css" rel="stylesheet">
</head>

<body ng-app="eventsApp" class="container-fluid" ng-controller="eventsCtrl">
<div class="table-responsive">
    <form class="form-group" name="newEvent">
        <div class="form-inline">
            <%--<label for="startDate">Start Date</label>--%>
            <%--<input class="datepicker" type="text" data-date-format="mm/dd/yyyy" readonly="true" id="startDate">--%>
            <div class="form-group">
                <div class="form-control">
                    <label>
                        <input class="checkbox" type="checkbox" ng-model="isTomorrow">是否横跨日期事件
                    </label>
                </div>
            </div>
            <div class="form-group">
                我在
                <select class="form-control" ng-model="hour" ng-options="h for h in hours">
                    <option value="">请选择小时</option>
                </select>
                <label>点</label>
            </div>
            <div class="form-group">
                <select class="form-control" ng-model="minute" ng-options="min for min in minutes">
                    <option value="">请选择分钟</option>
                </select>
                <label>分</label> 搞定了这件事情 ^_^
            </div>
            <div class="form-group">
                <button type="button" class="btn btn-default" ng-click="correctTime()">
                    重新校准时间
                </button>
            </div>
            <div class="form-group">
                <label>这件事情是 </label>
                <select class="form-control" ng-model="currentParentType"
                        ng-options="eventType.typeDescription for eventType in eventTypes | filter:filterParentType track by eventType.typeId">
                </select> 类型的
            </div>
        </div>
        <div class="form-group">
            <label>在下面做一个简要的描述吧</label>
            <textarea class="form-control" rows="3" ng-model="desc" type="textarea" required></textarea>
        </div>
        <div class="form-group">
            <label>有没有什么相关资源呢</label>
            <textarea class="form-control" rows="1" ng-model="eventResource" type="textarea"></textarea>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-default" ng-click="submit()" ng-disabled="!newEvent.$valid">都填完了，我要添加事件
            </button>
        </div>
    </form>
</div>
<div style="width: 100%; display: table;">
    <span class="time-line-cell" ng-repeat="config in timeLineCellConfigs" ng-click="showTimeLine($index)"
          style="width: {{config.width}}; background-color:{{config.bgcolor}};">nothing</span>
</div>
<div class="table-responsive">
    <ul class="list-group">
        <li class="list-group-item" ng-repeat="event in eventList | filter:filterMatchCode | orderBy:eventStartTime">
            <table class="table table-hover text-left">
                <thead>
                <tr>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>事件类型</th>
                    <th>事件描述</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td class="first-td">{{event.eventStartTime}}</td>
                    <td class="first-td">{{event.eventEndTime}}</td>
                    <td class="first-td">{{event.eventType}}</td>
                    <td>{{event.eventDescription}}</td>
                </tr>
                </tbody>
            </table>
        </li>
    </ul>
</div>
</body>

</html>
<script src="//cdn.bootcss.com/angular.js/1.4.3/angular.min.js"></script>
<script src="//cdn.bootcss.com/angular-resource/1.5.0/angular-resource.min.js"></script>
<script src="<%= request.getServletContext().getContextPath()%>/js/jquery.min.js-2.2.1.js"></script>
<script src="//cdn.bootcss.com/angular-ui-bootstrap/0.13.2/ui-bootstrap.min.js"></script>
<script src="//cdn.bootcss.com/angular-ui-bootstrap/0.13.2/ui-bootstrap-tpls.min.js"></script>
<script src="<%= request.getServletContext().getContextPath()%>/js/sockjs-0.3.4.js"></script>
<script src="<%= request.getServletContext().getContextPath()%>/js/stomp.js"></script>
<script src="<%= request.getServletContext().getContextPath()%>/js/bootstrap-datepicker.js"></script>
<script src="<%= request.getServletContext().getContextPath()%>/js/app.js"></script>
<script>
    $(function () {
        $('.datepicker').datepicker({
            autoclose: true
        });
    });
    app.controller("eventsCtrl", function ($scope, $http, $resource, utils) {
        $scope.currentParentType;
        $scope.eventList = [];
        $scope.eventTypes = [];
        $scope.eventChildTypes = [];
        $scope.isTomorrow = false;
        $scope.hour = utils.currentHours();
        $scope.hours = utils.hours();
        $scope.minute = utils.currentMinutes();
        $scope.minutes = utils.minutes();
        $scope.timeLineCellConfigs = [];
        $scope.startDate = null;
        $scope.endDate = null;
        $scope.getTypeColor = function (type) {
            for (var i = 0; i < $scope.eventTypes.length; i++) {
                if ($scope.eventTypes[i].typeId == type) {
                    return $scope.eventTypes[i].color;
                }
            }
        }
        $scope.load = function () {
            $http.get("eventTypes").success(function (res) {
                $scope.eventTypes = res._embedded.eventTypes;
                $scope.currentParentType = $scope.eventTypes[0];
                $http.get("events/?s=eventStartTime,desc").success(function (res) {
                    $scope.eventList = res._embedded.events;
                    var sum = 0;
                    var flag = 0;
                    $scope.timeLineCellConfigs = [];
                    angular.forEach($scope.eventList, function (value, key) {
                        sum = sum + value.eventDuration;
                    });
                    angular.forEach($scope.eventList, function (value, key) {
                        var config = {};
                        config.bgcolor = $scope.getTypeColor(value.eventType);
                        config.width = (value.eventDuration / sum) * 100 + "%";
                        $scope.timeLineCellConfigs.push(config);
                        flag = flag + 1;
                    });
                });
            });
        };
        $scope.load();
        $scope.filterParentType = function (element) {
            if (element.parentTypeId == 0) {
                return true;
            }
            return false;
        };
        $scope.showTimeLine = function (index) {
            alert($scope.eventList[index].eventDescription);
        };
        $scope.correctTime = function () {
            $scope.hour = utils.currentHours();
            $scope.minute = utils.currentMinutes();
        };
        $scope.desc;
        $scope.eventResource;
        $scope.submit = function () {
            if (!$scope.desc) {
                alert("desc null");
                return;
            }
            var day = parseInt($scope.eventList[0].eventEndTime.substr(8, 2));
            if ($scope.isTomorrow) {
                day = day + 1;
            }
            var event = {
                "eventStartTime": $scope.eventList[0].eventEndTime + ":00",
                "eventEndTime": $scope.eventList[0].eventEndTime.substr(0, 8) + day + " " + utils.paddingZero($scope.hour) + ":" + utils.paddingZero($scope.minute) + ":00",
                "eventDescription": $scope.desc,
                "eventResources": $scope.eventResource,
                "eventType": $scope.currentParentType.typeId
            };
            $http.post("add", {
                "entity": JSON.stringify(event)
            }).success(function (res) {
                if (res.errorCode != 0) {
                    alert("添加失败" + res.errorMessage);
                } else {
                    $scope.load();
                    $scope.desc = "";
                }
            }).error(function (res) {
                alert("error");
            });
        };
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
    app.directive('ronnieValidator', function () {
        return {
            link: function postLink(scope, iElement, iAttrs) {
                iElement.bind('click', function () {
                    alert("hh")
                })
            }
        };
    })
</script>