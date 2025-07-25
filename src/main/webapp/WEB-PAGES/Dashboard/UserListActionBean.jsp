<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-PAGES/taglibs.jsp" %>

<s:layout-render name="/WEB-PAGES/index.jsp" title="Users">
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
        <script src="${pageContext.request.contextPath}/js/users.js"></script>


        <div class="hr-line-dashed" style="margin-top: 3px; margin-bottom: 7px; border-top: 1px dashed #ddd; background-color: inherit;"></div>
        <div class="row">
            <div class="ibox ">
                <div class="ibox-content" style="min-height: 540px">
                    <div class="products-table-container">
                        <table class="table table-striped table-bordered table-hover list-user" width="100%">
                        </table>
                    </div>
                </div>
            </div>
        </div>

    </s:layout-component>
</s:layout-render>