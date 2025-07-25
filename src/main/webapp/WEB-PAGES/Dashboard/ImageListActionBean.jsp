<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-PAGES/taglibs.jsp" %>

<s:layout-render name="/WEB-PAGES/index.jsp" title="FiMo">
    <s:layout-component name="wrapper">
        <style>

            .ibox-title {padding: 10px 15px 5px; min-height: 35px; border-width: 0px 0px 0px 10px}
            .ibox.float-e-margins{margin-bottom: 10px;}
            .ibox-content{padding: 5px 12px 7px 12px; min-height: 84px;}
            .wrapper-content {padding-top:10px; padding-bottom:20px;}
            .row.border-bottom {height: 51px}
            .row.vertical-align {height: 34px}
            .navbar.navbar-static-top {height: 50px}
            .float-e-margins .btn {margin-bottom: 0px;}
            .slider {padding: 0px 5px 10px 5px}
            .label {padding: 4px 6px; font-weight: 400; font-size: 12px;}
            .label-inverse {opacity: 0.7}
            .legendLabel {padding-right: 5px}
            .flot-chart {margin-left: -10px}
            h2 {font-size: 28px}
            h3, h4 {font-weight: 400}

            th.dt-center, td.dt-center { text-align: center; }
            th.dt-head-center { text-align: center; }
            td.dt-body-center { text-align: center; }
            td { padding: 5px 8px !important}
            .dataTables_wrapper {padding-bottom: 0px;}

            .project-content {padding: 0px 10px 0px 10px}
            .col-lg-6.project-content {padding-right: 0px}
            .project-people {width: 30px}
            .project-people img{width: 50px; height: 48px}
            .project-list table tr td {padding: 4px 7px 2px 7px}
            .badge-rank {position: absolute; margin-top: 0px; margin-left: -45px}

            .project-title h5{margin-top: 2px;margin-bottom: 5px;font-weight: 400;font-size: 12px}
            .project-title h4{margin-top: 2px;margin-bottom: 5px;font-weight: 400;font-size: 14px}
            .project-title h2{margin-top: 2px;margin-bottom: 5px;font-weight: 500;font-size: 20px}

            .label-money-count {position: absolute;margin-left: -20px; margin-top:25px;padding: 3px 4px;font-size: 10px;}
            .team-members img.img-circle {
                width: 33px;
                height: 34px;
                margin-bottom: 5px;
                margin-right: 3px;
                background-color: #E7EAEC;
            }
        </style>
        <!-- DataTables -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/plugins/dataTables/datatables.min.css">
        <script src="${pageContext.request.contextPath}/js/plugins/dataTables/datatables.min.js"></script>

        <!-- Toastr script -->
        <link href="${pageContext.request.contextPath}/css/plugins/toastr/toastr.min.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/js/plugins/toastr/toastr.min.js"></script>

        <!-- Source -->
        <script src="${pageContext.request.contextPath}/js/linq.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/date.format.js"></script>
        <script src="${pageContext.request.contextPath}/js/general.js"></script>
        <script src="${pageContext.request.contextPath}/js/images.js"></script>

        <div class="panel">
            <div class="panel-body">

                    <div class="p-x-1 col-md-12">
                        <div class="p-x-1 col-lg-2">
                            <fieldset class="form-group form-group-md">
                                <label>Uploaded</label>
                            </fieldset>
                            <fieldset class="form-group form-group-md">
                                <label>Uploader</label>
                            </fieldset>
                            <fieldset class="form-group form-group-md">
                                <label>Details</label>
                            </fieldset>
                        </div>
                        <div class="p-x-1 col-lg-10">
                            <fieldset class="form-group form-group-md">
                                <label><fmt:formatDate pattern="yyyy/dd/MM HH:mm:ss" value="${actionBean.data.createAt}" /></label>
                            </fieldset>
                            <fieldset class="form-group form-group-md">
                                <label>${actionBean.data.user.fullName}</label>
                            </fieldset>
                            <fieldset class="form-group form-group-md">
                                <label>${actionBean.data.details}</label>
                            </fieldset>
                            <fieldset class="form-group form-group-md">
                                <table width="500px">
                                    <tr>
                                        <td height="300px">
                                            <div id="mapxx" style="width: 500px;height:300px"></div>
                                        </td>
                                    </tr>

                                </table>

                            </fieldset>
                        </div>
                    </div>
                <s:form class="form-horizontal" beanclass="id.go.lipi.informatika.alboom.dashboard.web.pages.ImageListActionBean">
                    <s:hidden id="aid" name="id"/>
                    <div class="p-x-1 col-md-12">
                        <fieldset class="form-group form-group-md">
                            <label>Notes</label>
                            <s:textarea id="notes-area" class="form-control" name="notes"/>
                        </fieldset>
                        <button type="button" onclick="updateNotes()" class="btn btn-md btn-primary m-t-3 confirm">Edit</button>
                        <s:submit name="back" class="btn btn-md btn-primary m-t-3">Back</s:submit>
                    </div>
                </s:form>
            </div>
        </div>

        <div class="hr-line-dashed" style="margin-top: 3px; margin-bottom: 7px; border-top: 1px dashed #ddd; background-color: inherit;"></div>


        <div class="row">
            <div class="ibox ">
                <div class="ibox-content" style="min-height: 540px">
                    <div class="products-table-container">
                        <table class="table table-striped table-bordered table-hover image-list" width="100%">
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <script>
            function initMap() {
                var myLatLng = {lat: ${actionBean.data.latitude}, lng: ${actionBean.data.longitude}};

                var map = new google.maps.Map(document.getElementById('mapxx'), {
                    zoom: 8,
                    center: myLatLng,
                    zoomControl: false,
                    scaleControl: false,
                    scrollwheel: false,
                    disableDoubleClickZoom: true,
                    draggable: false,
                    disableDefaultUI: true
                });

                var marker = new google.maps.Marker({
                    position: myLatLng,
                    map: map
                });
            }
        </script>
        <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAmNd1_VgvOjDFn4A0XpLyd3pCObPCVvk0&signed_in=true&callback=initMap"></script>
    </s:layout-component>
</s:layout-render>