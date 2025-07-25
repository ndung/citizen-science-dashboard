<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-PAGES/taglibs.jsp" %>
<s:layout-render name="/WEB-PAGES/index.jsp" title="Data Map">
    <s:layout-component name="wrapper">
        <style>
            .ibox-title {padding: 7px 15px 7px; min-height: 36px;}
            .float-e-margins .btn {margin-bottom: 0px;}
            .wrapper-content {padding-top:10px; padding-bottom:20px;}
            .row.border-bottom {height: 51px}
            .row.vertical-align {height: 34px}
            .navbar.navbar-static-top {height: 50px}
            .control-label {font-weight: 400;}
        </style>
        <link href="${pageContext.request.contextPath}/css/plugins/datapicker/datepicker3.css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet"/>

        <!-- Source -->
        <script src="${pageContext.request.contextPath}/js/date.format.js"></script>
        <script src="${pageContext.request.contextPath}/js/plugins/datapicker/datepicker.js"></script>
        <script src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/plugins/markerclusterer/markerclusterer.js"></script>
        <script src="${pageContext.request.contextPath}/js/geomap.cluster.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?libraries=places&key=AIzaSyAmNd1_VgvOjDFn4A0XpLyd3pCObPCVvk0"></script>

        <div class="row">
            <div class="col-lg-15">
                <div class="ibox float-e-margins" style="margin-bottom: 5px">
                    <div class="ibox-title">
                        <div class="ibox-tools">
                            <a id="stat-root" href="" class="btn btn-primary btn-xs" style="display: none;"></a>
                            <a id="stat-status" href="" class="btn btn-primary btn-xs" style="display: none;"></a>
                            <a id="stat-upline" href="" class="btn btn-primary btn-xs" style="display: none;"></a>
                            <a id="stat-dateJoint" href="" class="btn btn-primary btn-xs" style="display: none;"></a>
                            <a id="stat-lastLogin" href="" class="btn btn-primary btn-xs" style="display: none;"></a>
                            <a id="stat-isWebUser" href="" class="btn btn-primary btn-xs" style="display: none;"></a>
                            <a class="dropdown-toggle" data-toggle="modal" data-target="#modFilter">
                                <i class="fa fa-wrench"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content" style="padding: 15px">
                        <div id="map-container">
                            <div id="map" style="width: 1065px; height: 485px"></div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal inmodal" id="modFilter" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog modal-sm" style="width: 800px">
                    <s:form beanclass="id.go.lipi.informatika.alboom.dashboard.web.pages.GeoMapClusterActionBean">
                        <div class="modal-content animated fadeIn">
                            <div class="modal-header" style="padding: 10px 10px">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">x</span><span class="sr-only">Close</span></button>
                                <i class="fa fa-globe" style="zoom: 3;"></i>
                                <h3 id="mod-detail-name" style="margin:10px 0 0 0">Setup for Maps</h3>
                                <small id="mod-detail-location" class="font-bold">Filter based on below criterias:</small>
                            </div>
                            <div class="modal-body" style="padding: 10px 10px 5px 5px">
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <label class="col-xs-3 control-label">Verification:</label>
                                        <div class="col-xs-9">
                                            <s:hidden name="status" id="status"/>
                                            <div class="checkbox checkbox-primary checkbox-inline">
                                                <input id="checkboxUnverified" type="checkbox">
                                                <label for="checkboxUnverified">Unverified</label>
                                            </div>
                                            <div class="checkbox checkbox-warning checkbox-inline">
                                                <input id="checkboxPass" type="checkbox">
                                                <label for="checkboxPass">Pass</label>
                                            </div>
                                            <div class="checkbox checkbox-warning checkbox-inline">
                                                <input id="checkboxFail" type="checkbox">
                                                <label for="checkboxFail">Fail</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-xs-3 control-label">User name:</label>
                                        <div class="col-xs-9">
                                            <s:text id="user" name="user" class="form-control" style="border-radius: 4px">
                                            </s:text>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-xs-3 control-label">Uploaded date:</label>
                                        <div class="col-xs-9">
                                            <div class="input-daterange input-group" id="datepicker">
                                                <s:text class="input-sm form-control" name="uploadFrom" id="uploadFrom"/>
                                                <span class="input-group-addon">to</span>
                                                <s:text class="input-sm form-control" name="uploadTo" id="uploadTo"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-white" onclick="updateSettings()" data-dismiss="modal">Update</button>
                                <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </s:form>
                </div>
            </div>
        </div>

    </s:layout-component>
</s:layout-render>
