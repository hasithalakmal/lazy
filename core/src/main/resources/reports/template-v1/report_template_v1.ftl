<!DOCTYPE html>
<html>
<head>

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.js"></script>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    <style type="text/css">
        body {
            padding: 10px;
            background: #fff;
        }

        .wrapper {
            width: 100%;
        }

        @media (max-width: 992px) {
            .wrapper {
                width: 100%;
            }
        }

        .panel-heading {
            padding: 0;
            border: 0;
        }

        .panel-title > a, .panel-title > a:active {
            display: block;
            padding: 15px;
            color: #555;
            font-size: 16px;
            font-weight: bold;
            text-transform: uppercase;
            letter-spacing: 1px;
            word-spacing: 3px;
            text-decoration: none;
        }

        .panel-heading a:before {
            font-family: 'Glyphicons Halflings';
            content: "\e114";
            float: right;
            transition: all 0.5s;
        }

        .panel-heading.active a:before {
            -webkit-transform: rotate(180deg);
            -moz-transform: rotate(180deg);
            transform: rotate(180deg);
        }

        .row-space {
            height: 10px;
        }
    </style>
    <script>
        $(document).ready(function () {
            $('.panel-collapse').on('show.bs.collapse', function () {
                $(this).siblings('.panel-heading').addClass('active');
            });

            $('.panel-collapse').on('hide.bs.collapse', function () {
                $(this).siblings('.panel-heading').removeClass('active');
            });

            var chartOptions = {
                cutoutPercentage: 70,
                segmentShowStroke: false,
                animateScale: true,
                duration: 5000,
                legend: {
                    labels: {pointStyle: 'circle', usePointStyle: true}
                    // labels: {
                    //         render: 'label',
                    //         position: 'outside'
                    //     },
                }
            };

            //Root level
            var lazy_tsu_executionStatusData = {
                labels: ['Executed', 'Skipped', 'Failed', "Invalid"],
                datasets: [
                    {
                        backgroundColor: ["#28a745", "#ffc107", "#dc3545", "#6c757d"],
                        borderWidth: 0,
                        data: [${report.totalExecutedTestSuitesCount}, ${report.totalSkippedTestSuitesCount}, ${report.totalFailedTestSuitesCount}, ${report.totalInvalidTestSuitesCount}]
                    }
                ]
            };
            var lazy_tsu_executionStatusChart = document.getElementById("lazy_tsu_executionStatusChart");
            if (lazy_tsu_executionStatusChart) {
                new Chart(lazy_tsu_executionStatusChart, {
                    type: 'pie',
                    data: lazy_tsu_executionStatusData,
                    options: chartOptions
                });
            }

            var lazy_tsu_executionResultData = {
                labels: ['Pass', 'Fail'],
                datasets: [
                    {
                        backgroundColor: ["#28a745", "#dc3545"],
                        borderWidth: 0,
                        data: [${report.passTestSuitesCount}, ${report.notPassTestSuitesCount}]
                    }
                ]
            };
            var lazy_tsu_executionResultChart = document.getElementById("lazy_tsu_executionResultChart");
            if (lazy_tsu_executionResultChart) {
                new Chart(lazy_tsu_executionResultChart, {
                    type: 'pie',
                    data: lazy_tsu_executionResultData,
                    options: chartOptions
                });
            }

            var lazy_ts_executionStatusData = {
                labels: ['Executed', 'Skipped', 'Failed', "Invalid"],
                datasets: [
                    {
                        backgroundColor: ["#28a745", "#ffc107", "#dc3545", "#6c757d"],
                        borderWidth: 0,
                        data: [14, 3, 2, 1]
                    }
                ]
            };
            var lazy_ts_executionStatusChart = document.getElementById("lazy_ts_executionStatusChart");
            if (lazy_ts_executionStatusChart) {
                new Chart(lazy_ts_executionStatusChart, {
                    type: 'pie',
                    data: lazy_ts_executionStatusData,
                    options: chartOptions
                });
            }

            var lazy_ts_executionResultData = {
                labels: ['Pass', 'Fail'],
                datasets: [
                    {
                        backgroundColor: ["#28a745", "#dc3545"],
                        borderWidth: 0,
                        data: [${report.totalTestScenariosCount}, ${report.passTestScenariosCount}]
                    }
                ]
            };
            var lazy_ts_executionResultChart = document.getElementById("lazy_ts_executionResultChart");
            if (lazy_ts_executionResultChart) {
                new Chart(lazy_ts_executionResultChart, {
                    type: 'pie',
                    data: lazy_ts_executionResultData,
                    options: chartOptions
                });
            }




            var lazy_tc_executionStatusData = {
                labels: ['Executed', 'Skipped', 'Failed', "Invalid"],
                datasets: [
                    {
                        backgroundColor: ["#28a745", "#ffc107", "#dc3545", "#6c757d"],
                        borderWidth: 0,
                        data: [${report.totalExecutedTestCasesCount}, ${report.totalSkippedTestCasesCount}, ${report.totalFailedTestCasesCount}, ${report.totalInvalidTestCasesCount}]
                    }
                ]
            };
            var lazy_tc_executionStatusChart = document.getElementById("lazy_tc_executionStatusChart");
            if (lazy_tc_executionStatusChart) {
                new Chart(lazy_tc_executionStatusChart, {
                    type: 'pie',
                    data: lazy_tc_executionStatusData,
                    options: chartOptions
                });
            }

            var lazy_tc_executionResultData = {
                labels: ['Pass', 'Fail'],
                datasets: [
                    {
                        backgroundColor: ["#28a745", "#dc3545"],
                        borderWidth: 0,
                        data: [${report.totalTestCasesCount}, ${report.passTestCasesCount}]
                    }
                ]
            };
            var lazy_tc_executionResultChart = document.getElementById("lazy_tc_executionResultChart");
            if (lazy_tc_executionResultChart) {
                new Chart(lazy_tc_executionResultChart, {
                    type: 'pie',
                    data: lazy_tc_executionResultData,
                    options: chartOptions
                });
            }





            var lazy_ac_executionStatusData = {
                labels: ['Executed', 'Skipped', 'Failed', "Invalid"],
                datasets: [
                    {
                        backgroundColor: ["#28a745", "#ffc107", "#dc3545", "#6c757d"],
                        borderWidth: 0,
                        data: [${report.totalExecutedApiCallCount}, ${report.totalSkippedApiCallCount}, ${report.totalFailedApiCallCount}, ${report.totalInvalidApiCallCount}]
                    }
                ]
            };
            var lazy_ac_executionStatusChart = document.getElementById("lazy_ac_executionStatusChart");
            if (lazy_ac_executionStatusChart) {
                new Chart(lazy_ac_executionStatusChart, {
                    type: 'pie',
                    data: lazy_ac_executionStatusData,
                    options: chartOptions
                });
            }

            var lazy_ac_executionResultData = {
                labels: ['Pass', 'Fail'],
                datasets: [
                    {
                        backgroundColor: ["#28a745", "#dc3545"],
                        borderWidth: 0,
                        data: [${report.passApiCallCount}, ${report.notPassApiCallCount}]
                    }
                ]
            };
            var lazy_ac_executionResultChart = document.getElementById("lazy_ac_executionResultChart");
            if (lazy_ac_executionResultChart) {
                new Chart(lazy_ac_executionResultChart, {
                    type: 'pie',
                    data: lazy_ac_executionResultData,
                    options: chartOptions
                });
            }





            //Inside Test Scenario
<#list report.testSuiteReportList as testSuiteReport>
            var tsu${testSuiteReport.id}_ts_executionStatusData = {
                labels: ['Executed', 'Skipped', 'Failed', "Invalid"],
                datasets: [
                    {
                        backgroundColor: ["#28a745", "#ffc107", "#dc3545", "#6c757d"],
                        borderWidth: 0,
                        data: [${testSuiteReport.totalExecutedTestScenariosCount}, ${testSuiteReport.totalSkippedTestScenariosCount}, ${testSuiteReport.totalFailedTestScenariosCount}, ${testSuiteReport.totalInvalidTestScenariosCount}]
                    }
                ]
            };
            var tsu${testSuiteReport.id}_ts_executionStatusChart = document.getElementById("tsu${testSuiteReport.id}_ts_executionStatusChart");
            if (tsu${testSuiteReport.id}_ts_executionStatusChart) {
                new Chart(tsu${testSuiteReport.id}_ts_executionStatusChart, {
                    type: 'pie',
                    data: tsu${testSuiteReport.id}_ts_executionStatusData,
                    options: chartOptions
                });
            }

            var tsu${testSuiteReport.id}_ts_executionResultData = {
                labels: ['Pass', 'Fail'],
                datasets: [
                    {
                        backgroundColor: ["#28a745", "#dc3545"],
                        borderWidth: 0,
                        data: [70, 30]
                    }
                ]
            };
            var tsu${testSuiteReport.id}_ts_executionResultChart = document.getElementById("tsu${testSuiteReport.id}_ts_executionResultChart");
            if (tsu${testSuiteReport.id}_ts_executionResultChart) {
                new Chart(tsu${testSuiteReport.id}_ts_executionResultChart, {
                    type: 'pie',
                    data: tsu${testSuiteReport.id}_ts_executionResultData,
                    options: chartOptions
                });
            }

            var tsu${testSuiteReport.id}_tc_executionStatusData = {
                labels: ['Executed', 'Skipped', 'Failed', "Invalid"],
                datasets: [
                    {
                        backgroundColor: ["#28a745", "#ffc107", "#dc3545", "#6c757d"],
                        borderWidth: 0,
                        data: [90, 5, 5, 0]
                    }
                ]
            };
            var tsu${testSuiteReport.id}_tc_executionStatusChart = document.getElementById("tsu${testSuiteReport.id}_tc_executionStatusChart");
            if (tsu${testSuiteReport.id}_tc_executionStatusChart) {
                new Chart(tsu${testSuiteReport.id}_tc_executionStatusChart, {
                    type: 'pie',
                    data: tsu${testSuiteReport.id}_tc_executionStatusData,
                    options: chartOptions
                });
            }

            var tsu${testSuiteReport.id}_tc_executionResultData = {
                labels: ['Pass', 'Fail'],
                datasets: [
                    {
                        backgroundColor: ["#28a745", "#dc3545"],
                        borderWidth: 0,
                        data: [75, 25]
                    }
                ]
            };
            var tsu${testSuiteReport.id}_tc_executionResultChart = document.getElementById("tsu${testSuiteReport.id}_tc_executionResultChart");
            if (tsu${testSuiteReport.id}_tc_executionResultChart) {
                new Chart(tsu${testSuiteReport.id}_tc_executionResultChart, {
                    type: 'pie',
                    data: tsu${testSuiteReport.id}_tc_executionResultData,
                    options: chartOptions
                });
            }

            var tsu${testSuiteReport.id}_ac_executionStatusData = {
                labels: ['Executed', 'Skipped', 'Failed', "Invalid"],
                datasets: [
                    {
                        backgroundColor: ["#28a745", "#ffc107", "#dc3545", "#6c757d"],
                        borderWidth: 0,
                        data: [95, 0, 5, 0]
                    }
                ]
            };

            var tsu${testSuiteReport.id}_ac_executionStatusChart = document.getElementById("tsu${testSuiteReport.id}_ac_executionStatusChart");
            if (tsu${testSuiteReport.id}_ac_executionStatusChart) {
                new Chart(tsu${testSuiteReport.id}_ac_executionStatusChart, {
                    type: 'pie',
                    data: tsu${testSuiteReport.id}_ac_executionStatusData,
                    options: chartOptions
                });
            }

            var tsu${testSuiteReport.id}_ac_executionResultData = {
                labels: ['Pass', 'Fail'],
                datasets: [
                    {
                        backgroundColor: ["#28a745", "#dc3545"],
                        borderWidth: 0,
                        data: [90, 10]
                    }
                ]
            };
            var tsu${testSuiteReport.id}_ac_executionResultChart = document.getElementById("tsu${testSuiteReport.id}_ac_executionResultChart");
            if (tsu${testSuiteReport.id}_ac_executionResultChart) {
                new Chart(tsu${testSuiteReport.id}_ac_executionResultChart, {
                    type: 'pie',
                    data: tsu${testSuiteReport.id}_ac_executionResultData,
                    options: chartOptions
                });
            }


  <#list testSuiteReport.testScenarioReports as testScenarioReport>
            //Charts Inside Test Scenario1
            var tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc_executionStatusData = {
                labels: ['Executed', 'Skipped', 'Failed', "Invalid"],
                datasets: [
                    {
                        backgroundColor: ["#28a745", "#ffc107", "#dc3545", "#6c757d"],
                        borderWidth: 0,
                        data: [14, 3, 2, 1]
                    }
                ]
            };
            var tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc_executionStatusChart = document.getElementById("tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc_executionStatusChart");
            if (tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc_executionStatusChart) {
                new Chart(tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc_executionStatusChart, {
                    type: 'pie',
                    data: tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc_executionStatusData,
                    options: chartOptions
                });
            }

            var tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc_executionResultData = {
                labels: ['Pass', 'Fail'],
                datasets: [
                    {
                        backgroundColor: ["#28a745", "#dc3545"],
                        borderWidth: 0,
                        data: [70, 30]
                    }
                ]
            };
            var tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc_executionResultChart = document.getElementById("tsu${testSuiteReport.id}_ts${testSuiteReport.id}_tc_executionResultChart");
            if (tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc_executionResultChart) {
                new Chart(tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc_executionResultChart, {
                    type: 'pie',
                    data: tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc_executionResultData,
                    options: chartOptions
                });
            }

            var tsu${testSuiteReport.id}_ts${testScenarioReport.id}_ac_executionStatusData = {
                labels: ['Executed', 'Skipped', 'Failed', "Invalid"],
                datasets: [
                    {
                        backgroundColor: ["#28a745", "#ffc107", "#dc3545", "#6c757d"],
                        borderWidth: 0,
                        data: [14, 3, 2, 1]
                    }
                ]
            };
            var tsu${testSuiteReport.id}_ts${testScenarioReport.id}_ac_executionStatusChart = document.getElementById("tsu${testSuiteReport.id}_ts${testScenarioReport.id}_ac_executionStatusChart");
            if (tsu${testSuiteReport.id}_ts${testScenarioReport.id}_ac_executionStatusChart) {
                new Chart(tsu${testSuiteReport.id}_ts${testScenarioReport.id}_ac_executionStatusChart, {
                    type: 'pie',
                    data: tsu${testSuiteReport.id}_ts${testScenarioReport.id}_ac_executionStatusData,
                    options: chartOptions
                });
            }

            var tsu${testSuiteReport.id}_ts${testScenarioReport.id}_ac_executionResultData = {
                labels: ['Pass', 'Fail'],
                datasets: [
                    {
                        backgroundColor: ["#28a745", "#dc3545"],
                        borderWidth: 0,
                        data: [70, 30]
                    }
                ]
            };
            var tsu${testSuiteReport.id}_ts${testScenarioReport.id}_ac_executionResultChart = document.getElementById("tsu${testSuiteReport.id}_ts${testScenarioReport.id}_ac_executionResultChart");
            if (tsu${testSuiteReport.id}_ts${testScenarioReport.id}_ac_executionResultChart) {
                new Chart(tsu${testSuiteReport.id}_ts${testScenarioReport.id}_ac_executionResultChart, {
                    type: 'pie',
                    data: tsu${testSuiteReport.id}_ts${testScenarioReport.id}_ac_executionResultData,
                    options: chartOptions
                });
            }

      <#list testScenarioReport.testCaseReports as testCaseReport>
            //Inside test case
            var tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac_executionStatusData = {
                labels: ['Executed', 'Skipped', 'Failed', "Invalid"],
                datasets: [
                    {
                        backgroundColor: ["#28a745", "#ffc107", "#dc3545", "#6c757d"],
                        borderWidth: 0,
                        data: [14, 3, 2, 1]
                    }
                ]
            };
            var tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac_executionStatusChart = document.getElementById("tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac_executionStatusChart");
            if (tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac_executionStatusChart) {
                new Chart(tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac_executionStatusChart, {
                    type: 'pie',
                    data: tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac_executionStatusData,
                    options: chartOptions
                });
            }

            var tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac_executionResultData = {
                labels: ['Pass', 'Fail'],
                datasets: [
                    {
                        backgroundColor: ["#28a745", "#dc3545"],
                        borderWidth: 0,
                        data: [70, 30]
                    }
                ]
            };
            var tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac_executionResultChart = document.getElementById("tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac_executionResultChart");
            if (tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac_executionResultChart) {
                new Chart(tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac_executionResultChart, {
                    type: 'pie',
                    data: tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac_executionResultData,
                    options: chartOptions
                });
            }


            <#list testCaseReport.apiCallReports as apiCallReport>
            //API Call
            var tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac${apiCallReport.id}_ass_executionStatusData = {
                labels: ['Executed', 'Skipped', 'Failed', "Invalid"],
                datasets: [
                    {
                        backgroundColor: ["#28a745", "#ffc107", "#dc3545", "#6c757d"],
                        borderWidth: 0,
                        data: [14, 3, 2, 1]
                    }
                ]
            };
            var tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac${apiCallReport.id}_ass_executionStatusChart = document.getElementById("tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac${apiCallReport.id}_ass_executionStatusChart");
            if (tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac${apiCallReport.id}_ass_executionStatusChart) {
                new Chart(tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac${apiCallReport.id}_ass_executionStatusChart, {
                    type: 'pie',
                    data: tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac${apiCallReport.id}_ass_executionStatusData,
                    options: chartOptions
                });
            }

            var tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac${apiCallReport.id}_ass_executionResultData = {
                labels: ['Pass', 'Fail'],
                datasets: [
                    {
                        backgroundColor: ["#28a745", "#dc3545"],
                        borderWidth: 0,
                        data: [70, 30]
                    }
                ]
            };
            var tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac${apiCallReport.id}_ass_executionResultChart = document.getElementById("tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac${apiCallReport.id}_ass_executionResultChart");
            if (tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac${apiCallReport.id}_ass_executionResultChart) {
                new Chart(tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac${apiCallReport.id}_ass_executionResultChart, {
                    type: 'pie',
                    data: tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac${apiCallReport.id}_ass_executionResultData,
                    options: chartOptions
                });
            }
            </#list>
        </#list>
   </#list>
</#list>
        });


    </script>
</head>
<body>
<div class="wrapper center-block">
    <div class="container">
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <div class="alert alert-warning">
                        <div>
                            <h1 align="center">LAZY Execution Report</h1>
                            <p align="center">(${report.date})</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row-space">
            </div>
            <div class="row">
                <div class="col-sm-4 ">
                    <div class="alert alert-info">
                        <strong>Total Test Suites ${report.totalTestSuitesCount}</strong>
                    </div>
                </div>
                <div class="col-sm-4 ">
                    <div class="alert alert-success">
                        <strong>Pass Test Suites ${report.passTestSuitesCount}</strong>
                    </div>
                </div>
                <div class="col-sm-4 ">
                    <div class="alert alert-danger">
                        <strong>Failed Test Suites ${report.notPassTestSuitesCount}</strong>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-8">
                    <h3 style="color: #3E2723">Test Suite Execution Status Summery</h3>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Execution Status</th>
                            <th>Count</th>
                            <th>Percentage</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Executed</td>
                            <td>${report.totalExecutedTestSuitesCount}</td>
                            <td>${report.executedTestSuitesPercentage}%</td>
                        </tr>
                        <tr>
                            <td>Skipped</td>
                            <td>${report.totalSkippedTestSuitesCount}</td>
                            <td>${report.skippedTestSuitesPercentage}%</td>
                        </tr>
                        <tr>
                            <td>Failed</td>
                            <td>${report.totalFailedTestSuitesCount}</td>
                            <td>${report.failedTestSuitesPercentage}%</td>
                        </tr>
                        <tr>
                            <td>Invalid</td>
                            <td>${report.totalInvalidTestSuitesCount}</td>
                            <td>${report.invalidTestSuitesPercentage}%</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-sm-4">
                    <div class="card">
                        <div class="card-body" style="width: 70%; alignment: center">
                            <canvas id="lazy_tsu_executionStatusChart" style="alignment: center"></canvas>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-8">
                    <h3 style="color: #3E2723">Test Suite Execution Result Summery</h3>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Result</th>
                            <th>Count</th>
                            <th>Percentage</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Pass</td>
                            <td>${report.passTestSuitesCount}</td>
                            <td>${report.passTestSuitesPercentage}%</td>
                        </tr>
                        <tr>
                            <td>Failed</td>
                            <td>${report.notPassTestSuitesCount}</td>
                            <td>${report.notPassTestSuitesPercentage}%</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-sm-4">
                    <div class="card">
                        <div class="card-body" style="width: 70%; alignment: center">
                            <canvas id="lazy_tsu_executionResultChart" style="alignment: center"></canvas>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row-space">
                <Hr/>
            </div>
            <div class="row">
                <div class="col-sm-8">
                    <h3 style="color: #3E2723">Test Scenario Execution Status Summery</h3>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Execution Status</th>
                            <th>Count</th>
                            <th>Percentage</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Executed</td>
                            <td>${report.totalExecutedTestScenariosCount}</td>
                            <td>${report.executedTestScenariosPercentage}%</td>
                        </tr>
                        <tr>
                            <td>Skipped</td>
                            <td>${report.totalSkippedTestScenariosCount}</td>
                            <td>${report.skippedTestScenariosPercentage}%</td>
                        </tr>
                        <tr>
                            <td>Failed</td>
                            <td>${report.totalFailedTestScenariosCount}</td>
                            <td>${report.failedTestScenariosPercentage}%</td>
                        </tr>
                        <tr>
                            <td>Invalid</td>
                            <td>${report.totalInvalidTestScenariosCount}</td>
                            <td>${report.invalidTestScenariosPercentage}%</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-sm-4">
                    <div class="card">
                        <div class="card-body" style="width: 70%; alignment: center">
                            <canvas id="lazy_ts_executionStatusChart" style="alignment: center"></canvas>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-8">
                    <h3 style="color: #3E2723">Test Scenario Execution Result Summery</h3>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Result</th>
                            <th>Count</th>
                            <th>Percentage</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Pass</td>
                            <td>${report.passTestScenariosCount}</td>
                            <td>${report.passTestScenariosPercentage}%</td>
                        </tr>
                        <tr>
                            <td>Failed</td>
                            <td>${report.notPassTestScenariosCount}</td>
                            <td>${report.notPassTestScenariosPercentage}%</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-sm-4">
                    <div class="card">
                        <div class="card-body" style="width: 70%; alignment: center">
                            <canvas id="lazy_ts_executionResultChart" style="alignment: center"></canvas>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row-space">
                <Hr/>
            </div>
            <div class="row">
                <div class="col-sm-8">
                    <h3 style="color: #3E2723">Test Case Execution Status Summery</h3>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Execution Status</th>
                            <th>Count</th>
                            <th>Percentage</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Executed</td>
                            <td>${report.totalExecutedTestCasesCount}</td>
                            <td>${report.executedTestCasesPercentage}%</td>
                        </tr>
                        <tr>
                            <td>Skipped</td>
                            <td>${report.totalSkippedTestCasesCount}</td>
                            <td>>${report.skippedTestCasesPercentage}%</td>
                        </tr>
                        <tr>
                            <td>Failed</td>
                            <td>${report.totalFailedTestCasesCount}</td>
                            <td>${report.failedTestCasesPercentage}%</td>
                        </tr>
                        <tr>
                            <td>Invalid</td>
                            <td>${report.totalInvalidTestCasesCount}</td>
                            <td>${report.invalidTestCasesPercentage}%</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-sm-4">
                    <div class="card">
                        <div class="card-body" style="width: 70%; alignment: center">
                            <canvas id="lazy_tc_executionStatusChart" style="alignment: center"></canvas>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-8">
                    <h3 style="color: #3E2723">Test Case Execution Result Summery</h3>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Result</th>
                            <th>Count</th>
                            <th>Percentage</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Pass</td>
                            <td>${report.passTestCasesCount}</td>
                            <td>${report.passTestCasesPercentage}%</td>
                        </tr>
                        <tr>
                            <td>Failed</td>
                            <td>${report.notPassTestCasesCount}</td>
                            <td>${report.notPassTestCasesPercentage}%</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-sm-4">
                    <div class="card">
                        <div class="card-body" style="width: 70%; alignment: center">
                            <canvas id="lazy_tc_executionResultChart" style="alignment: center"></canvas>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row-space">
                <Hr/>
            </div>
            <div class="row">
                <div class="col-sm-8">
                    <h3 style="color: #3E2723">Api Call Execution Status Summery</h3>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Execution Status</th>
                            <th>Count</th>
                            <th>Percentage</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Executed</td>
                            <td>${report.totalExecutedApiCallCount}</td>
                            <td>${report.executedApiCallPercentage}%</td>
                        </tr>
                        <tr>
                            <td>Skipped</td>
                            <td>${report.totalSkippedApiCallCount}</td>
                            <td>${report.skippedApiCallPercentage}%</td>
                        </tr>
                        <tr>
                            <td>Failed</td>
                            <td>${report.totalFailedApiCallCount}</td>
                            <td>${report.failedApiCallPercentage}%</td>
                        </tr>
                        <tr>
                            <td>Invalid</td>
                            <td>${report.totalInvalidApiCallCount}</td>
                            <td>${report.invalidApiCallPercentage}%</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-sm-4">
                    <div class="card">
                        <div class="card-body" style="width: 70%; alignment: center">
                            <canvas id="lazy_ac_executionStatusChart" style="alignment: center"></canvas>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-8">
                    <h3 style="color: #3E2723">Api Call Execution Result Summery</h3>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Result</th>
                            <th>Count</th>
                            <th>Percentage</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Pass</td>
                            <td>${report.passApiCallCount}</td>
                            <td>${report.passApiCallPercentage}%</td>
                        </tr>
                        <tr>
                            <td>Failed</td>
                            <td>${report.notPassApiCallCount}</td>
                            <td>${report.notPassApiCallPercentage}%</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-sm-4">
                    <div class="card">
                        <div class="card-body" style="width: 70%; alignment: center">
                            <canvas id="lazy_ac_executionResultChart" style="alignment: center"></canvas>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="row-space"></div>
    </div>
    <div class="panel-group" id="testSuiteData" role="tablist" aria-multiselectable="true">
        <#list report.testSuiteReportList as testSuiteReport>
            <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="testSuiteHeading${testSuiteReport.id}" style="background-color: #64B5F6">
                <h4 class="panel-title">
                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#testSuiteData" href="#testSuiteCollapse${testSuiteReport.id}"
                       aria-expanded="false" aria-controls="testSuiteCollapse${testSuiteReport.id}">
                        [Test Suite] ${testSuiteReport.testSuiteName} (${testSuiteReport.passTestScenariosCount}/${testSuiteReport
                        .totalTestScenariosCount})
                    </a>
                </h4>
            </div>
            <div id="testSuiteCollapse${testSuiteReport.id}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="testSuiteHeading${testSuiteReport.id}">
                <div class="panel-body">
                    <div class="container">
                        <!--Test Scenario Report-->
                        <div class="container">
                            <div class="row">
                                <div class="col-sm-4 ">
                                    <div class="alert alert-info">
                                        <strong>Total Test Scenarios ${testSuiteReport.totalTestScenariosCount}</strong>
                                    </div>
                                </div>
                                <div class="col-sm-4 ">
                                    <div class="alert alert-success">
                                        <strong>Pass Test Scenarios ${testSuiteReport.passTestScenariosCount}</strong>
                                    </div>
                                </div>
                                <div class="col-sm-4 ">
                                    <div class="alert alert-danger">
                                        <strong>Failed Test Scenarios ${testSuiteReport.notPassTestScenariosCount}</strong>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-8">
                                    <h3 style="color: #3E2723">Test Scenario Execution Status Summery</h3>
                                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th>Execution Status</th>
                                            <th>Count</th>
                                            <th>Percentage</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>Executed</td>
                                            <td>${testSuiteReport.totalExecutedTestScenariosCount}</td>
                                            <td>${testSuiteReport.executedTestScenariosPercentage}%</td>
                                        </tr>
                                        <tr>
                                            <td>Skipped</td>
                                            <td>${testSuiteReport.totalSkippedTestScenariosCount}</td>
                                            <td>${testSuiteReport.skippedTestScenariosPercentage}%</td>
                                        </tr>
                                        <tr>
                                            <td>Failed</td>
                                            <td>${testSuiteReport.totalFailedTestScenariosCount}</td>
                                            <td>${testSuiteReport.failedTestScenariosPercentage}%</td>
                                        </tr>
                                        <tr>
                                            <td>Invalid</td>
                                            <td>${testSuiteReport.totalInvalidTestScenariosCount}</td>
                                            <td>${testSuiteReport.invalidTestScenariosPercentage}%</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="col-sm-4">
                                    <div class="card">
                                        <div class="card-body" style="width: 70%; alignment: center">
                                            <canvas id="tsu${testSuiteReport.id}_ts_executionStatusChart" style="alignment: center"></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-8">
                                    <h3 style="color: #3E2723">Test Scenario Execution Result Summery</h3>
                                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th>Result</th>
                                            <th>Count</th>
                                            <th>Percentage</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>Pass</td>
                                            <td>14</td>
                                            <td>70%</td>
                                        </tr>
                                        <tr>
                                            <td>Failed</td>
                                            <td>6</td>
                                            <td>30%</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="col-sm-4">
                                    <div class="card">
                                        <div class="card-body" style="width: 70%; alignment: center">
                                            <canvas id="tsu${testSuiteReport.id}_ts_executionResultChart" style="alignment: center"></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row-space">
                            <hr/>
                        </div>
                        <!--Test Case Report-->
                        <div class="container">
                            <div class="row">
                                <div class="col-sm-8">
                                    <h3 style="color: #6D4C41">Test Case Execution Status Summery</h3>
                                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th>Execution Status</th>
                                            <th>Count</th>
                                            <th>Percentage</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>Executed</td>
                                            <td>90</td>
                                            <td>90%</td>
                                        </tr>
                                        <tr>
                                            <td>Skipped</td>
                                            <td>5</td>
                                            <td>5%</td>
                                        </tr>
                                        <tr>
                                            <td>Failed</td>
                                            <td>5</td>
                                            <td>5%</td>
                                        </tr>
                                        <tr>
                                            <td>Invalid</td>
                                            <td>0</td>
                                            <td>0%</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="col-sm-4">
                                    <div class="card">
                                        <div class="card-body" style="width: 70%; alignment: center">
                                            <canvas id="tsu${testSuiteReport.id}_tc_executionStatusChart" style="alignment: center"></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-8">
                                    <h3 style="color: #6D4C41">Test Case Execution Result Summery</h3>
                                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th>Result</th>
                                            <th>Count</th>
                                            <th>Percentage</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>Pass</td>
                                            <td>75</td>
                                            <td>75%</td>
                                        </tr>
                                        <tr>
                                            <td>Failed</td>
                                            <td>25</td>
                                            <td>25%</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="col-sm-4">
                                    <div class="card">
                                        <div class="card-body" style="width: 70%; alignment: center">
                                            <canvas id="tsu${testSuiteReport.id}_tc_executionResultChart" style="alignment: center"></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row-space">
                            <hr/>
                        </div>


                        <!--Api Call Report-->
                        <div class="container">
                            <div class="row">
                                <div class="col-sm-8">
                                    <h3 style="color: #A1887F">Api Call Execution Status Summery</h3>
                                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th>Execution Status</th>
                                            <th>Count</th>
                                            <th>Percentage</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>Executed</td>
                                            <td>95</td>
                                            <td>95%</td>
                                        </tr>
                                        <tr>
                                            <td>Skipped</td>
                                            <td>0</td>
                                            <td>0%</td>
                                        </tr>
                                        <tr>
                                            <td>Failed</td>
                                            <td>5</td>
                                            <td>5%</td>
                                        </tr>
                                        <tr>
                                            <td>Invalid</td>
                                            <td>0</td>
                                            <td>0%</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="col-sm-4">
                                    <div class="card">
                                        <div class="card-body" style="width: 70%; alignment: center">
                                            <canvas id="tsu${testSuiteReport.id}_ac_executionStatusChart" style="alignment: center"></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-8">
                                    <h3 style="color: #A1887F">Api Call Execution Result Summery</h3>
                                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th>Result</th>
                                            <th>Count</th>
                                            <th>Percentage</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>Pass</td>
                                            <td>80</td>
                                            <td>80%</td>
                                        </tr>
                                        <tr>
                                            <td>Failed</td>
                                            <td>20</td>
                                            <td>20%</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="col-sm-4">
                                    <div class="card">
                                        <div class="card-body" style="width: 70%; alignment: center">
                                            <canvas id="tsu${testSuiteReport.id}_ac_executionResultChart" style="alignment: center"></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row-space">
                    </div>

                    <!-- Test Scenario Panel-->
                    <div class="panel-group" id="testScenarioData${testSuiteReport.id}" role="tablist" aria-multiselectable="true">

            <#list testSuiteReport.testScenarioReports as testScenarioReport>
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="testScenarioHeading${testScenarioReport.id}" style="background-color: #90CAF9">
                                <h4 class="panel-title">
                                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#testScenarioData${testSuiteReport.id}"
                                       href="#testScenarioCollapse${testScenarioReport.id}" aria-expanded="false"
                                       aria-controls="testSuiteCollapse${testScenarioReport.id}">
                                        [Test Scenario] Create Account Successfully
                                    </a>
                                </h4>
                            </div>
                            <div id="testScenarioCollapse${testScenarioReport.id}" class="panel-collapse collapse" role="tabpanel"
                                 aria-labelledby="testScenarioHeading${testScenarioReport.id}">
                                <div class="panel-body">
                                    <div class="container">
                                        <!--Test Case Report-->
                                        <div class="container">
                                            <div class="row">
                                                <div class="col-sm-4 ">
                                                    <div class="alert alert-info">
                                                        <strong>Total Test Case</strong> 20
                                                    </div>
                                                </div>
                                                <div class="col-sm-4 ">
                                                    <div class="alert alert-success">
                                                        <strong>Pass Test Case</strong> 14
                                                    </div>
                                                </div>
                                                <div class="col-sm-4 ">
                                                    <div class="alert alert-danger">
                                                        <strong>Failed Test Case</strong> 6
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-8">
                                                    <h3 style="color: #3E2723">Test Case Execution Status Summery</h3>
                                                    <table class="table table-striped">
                                                        <thead>
                                                        <tr>
                                                            <th>Execution Status</th>
                                                            <th>Count</th>
                                                            <th>Percentage</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr>
                                                            <td>Executed</td>
                                                            <td>14</td>
                                                            <td>70%</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Skipped</td>
                                                            <td>3</td>
                                                            <td>15%</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Failed</td>
                                                            <td>2</td>
                                                            <td>10%</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Invalid</td>
                                                            <td>1</td>
                                                            <td>5%</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="card">
                                                        <div class="card-body" style="width: 70%; alignment: center">
                                                            <canvas id="tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc_executionStatusChart" style="alignment: center"></canvas>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-8">
                                                    <h3 style="color: #3E2723">Test Case Execution Result Summery</h3>
                                                    <table class="table table-striped">
                                                        <thead>
                                                        <tr>
                                                            <th>Result</th>
                                                            <th>Count</th>
                                                            <th>Percentage</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr>
                                                            <td>Pass</td>
                                                            <td>14</td>
                                                            <td>70%</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Failed</td>
                                                            <td>6</td>
                                                            <td>30%</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="card">
                                                        <div class="card-body" style="width: 70%; alignment: center">
                                                            <canvas id="tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc_executionResultChart" style="alignment: center"></canvas>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row-space">
                                            <hr/>
                                        </div>
                                        <!--Api Call Report-->
                                        <div class="container">
                                            <div class="row">
                                                <div class="col-sm-8">
                                                    <h3 style="color: #A1887F">Api Call Execution Status Summery</h3>
                                                    <table class="table table-striped">
                                                        <thead>
                                                        <tr>
                                                            <th>Execution Status</th>
                                                            <th>Count</th>
                                                            <th>Percentage</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr>
                                                            <td>Executed</td>
                                                            <td>95</td>
                                                            <td>95%</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Skipped</td>
                                                            <td>0</td>
                                                            <td>0%</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Failed</td>
                                                            <td>5</td>
                                                            <td>5%</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Invalid</td>
                                                            <td>0</td>
                                                            <td>0%</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="card">
                                                        <div class="card-body" style="width: 70%; alignment: center">
                                                            <canvas id="tsu${testSuiteReport.id}_ts${testScenarioReport.id}_ac_executionStatusChart" style="alignment: center"></canvas>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-8">
                                                    <h3 style="color: #A1887F">Api Call Execution Result Summery</h3>
                                                    <table class="table table-striped">
                                                        <thead>
                                                        <tr>
                                                            <th>Result</th>
                                                            <th>Count</th>
                                                            <th>Percentage</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr>
                                                            <td>Pass</td>
                                                            <td>80</td>
                                                            <td>80%</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Failed</td>
                                                            <td>20</td>
                                                            <td>20%</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="card">
                                                        <div class="card-body" style="width: 70%; alignment: center">
                                                            <canvas id="tsu${testSuiteReport.id}_ts${testScenarioReport.id}_ac_executionResultChart" style="alignment: center"></canvas>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row-space">
                                    </div>


                                    <!-- Test Case Panel-->
                                    <div class="panel-group" id="testcaseData_${testSuiteReport.id}_${testScenarioReport.id}" role="tablist"
                                         aria-multiselectable="true">

                        <#list testScenarioReport.testCaseReports as testCaseReport>
                                        <div class="panel panel-default">
                                            <div class="panel-heading" role="tab" id="testcaseHeading${testCaseReport.id}"
                                                 style="background-color: #BBDEFB">
                                                <h4 class="panel-title">
                                                    <a class="collapsed" role="button" data-toggle="collapse"
                                                       data-parent="#testcaseData_${testSuiteReport.id}_${testScenarioReport.id}"
                                                       href="#testcaseCollapse${testCaseReport.id}" aria-expanded="false"
                                                       aria-controls="testSuiteCollapse${testCaseReport.id}">
                                                        [Test Case] Create Simple Account
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="testcaseCollapse${testCaseReport.id}" class="panel-collapse collapse" role="tabpanel"
                                                 aria-labelledby="testcaseHeading${testCaseReport.id}">
                                                <div class="panel-body">
                                                    <div class="container">
                                                        <!--Api call Report-->
                                                        <div class="container">
                                                            <div class="row">
                                                                <div class="col-sm-4 ">
                                                                    <div class="alert alert-info">
                                                                        <strong>Total Api Call</strong> 20
                                                                    </div>
                                                                </div>
                                                                <div class="col-sm-4 ">
                                                                    <div class="alert alert-success">
                                                                        <strong>Pass Api Call</strong> 14
                                                                    </div>
                                                                </div>
                                                                <div class="col-sm-4 ">
                                                                    <div class="alert alert-danger">
                                                                        <strong>Failed Api Call</strong> 6
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-sm-8">
                                                                    <h3 style="color: #3E2723">Api Call Execution Status Summery</h3>
                                                                    <table class="table table-striped">
                                                                        <thead>
                                                                        <tr>
                                                                            <th>Execution Status</th>
                                                                            <th>Count</th>
                                                                            <th>Percentage</th>
                                                                        </tr>
                                                                        </thead>
                                                                        <tbody>
                                                                        <tr>
                                                                            <td>Executed</td>
                                                                            <td>14</td>
                                                                            <td>70%</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Skipped</td>
                                                                            <td>3</td>
                                                                            <td>15%</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Failed</td>
                                                                            <td>2</td>
                                                                            <td>10%</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Invalid</td>
                                                                            <td>1</td>
                                                                            <td>5%</td>
                                                                        </tr>
                                                                        </tbody>
                                                                    </table>
                                                                </div>
                                                                <div class="col-sm-4">
                                                                    <div class="card">
                                                                        <div class="card-body" style="width: 70%; alignment: center">
                                                                            <canvas id="tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac_executionStatusChart"
                                                                                    style="alignment: center"></canvas>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-sm-8">
                                                                    <h3 style="color: #3E2723">Api Call Execution Result Summery</h3>
                                                                    <table class="table table-striped">
                                                                        <thead>
                                                                        <tr>
                                                                            <th>Result</th>
                                                                            <th>Count</th>
                                                                            <th>Percentage</th>
                                                                        </tr>
                                                                        </thead>
                                                                        <tbody>
                                                                        <tr>
                                                                            <td>Pass</td>
                                                                            <td>14</td>
                                                                            <td>70%</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Failed</td>
                                                                            <td>6</td>
                                                                            <td>30%</td>
                                                                        </tr>
                                                                        </tbody>
                                                                    </table>
                                                                </div>
                                                                <div class="col-sm-4">
                                                                    <div class="card">
                                                                        <div class="card-body" style="width: 70%; alignment: center">
                                                                            <canvas id="tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac_executionResultChart"
                                                                                    style="alignment: center"></canvas>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row-space">
                                                    </div>

                                                    <!-- Api Call Panel-->
                                                    <div class="panel-group" id="apiCallData_${testSuiteReport.id}_${testScenarioReport
                                                    .id}_${testCaseReport.id}" role="tablist" aria-multiselectable="true">

                                             <#list testCaseReport.apiCallReports as apiCallReport>

                                                        <div class="panel panel-default">
                                                            <div class="panel-heading" role="tab" id="apiCallHeading${apiCallReport.id}"
                                                                 style="background-color: #E3F2FD">
                                                                <h4 class="panel-title">
                                                                    <a class="collapsed" role="button" data-toggle="collapse"
                                                                       data-parent="#apiCallData_${testSuiteReport.id}_${testScenarioReport
                                                                       .id}_${testCaseReport.id}"
                                                                       href="#apiCallCollapse${apiCallReport.id}" aria-expanded="false"
                                                                       aria-controls="testSuiteCollapse${apiCallReport.id}">
                                                                        [Api Call] Create Account API Call
                                                                    </a>
                                                                </h4>
                                                            </div>
                                                            <div id="apiCallCollapse${apiCallReport.id}" class="panel-collapse collapse" role="tabpanel"
                                                                 aria-labelledby="apiCallHeading${apiCallReport.id}">
                                                                <div class="panel-body">

                                                                    <div class="row">
                                                                        <div class="col-sm-4 ">
                                                                            <div class="alert alert-info">
                                                                                <strong>Total Assertions</strong> 27
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-4 ">
                                                                            <div class="alert alert-success">
                                                                                <strong>Pass Assertions</strong> 14
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-4 ">
                                                                            <div class="alert alert-danger">
                                                                                <strong>Failed Assertions</strong> 6
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="row">
                                                                        <div class="col-sm-8">
                                                                            <h3 style="color: #3E2723">Assertions Execution Status
                                                                                Summery</h3>
                                                                            <table class="table table-striped">
                                                                                <thead>
                                                                                <tr>
                                                                                    <th>Execution Status</th>
                                                                                    <th>Count</th>
                                                                                    <th>Percentage</th>
                                                                                </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                <tr>
                                                                                    <td>Executed</td>
                                                                                    <td>14</td>
                                                                                    <td>70%</td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td>Skipped</td>
                                                                                    <td>3</td>
                                                                                    <td>15%</td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td>Failed</td>
                                                                                    <td>2</td>
                                                                                    <td>10%</td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td>Invalid</td>
                                                                                    <td>1</td>
                                                                                    <td>5%</td>
                                                                                </tr>
                                                                                </tbody>
                                                                            </table>
                                                                        </div>
                                                                        <div class="col-sm-4">
                                                                            <div class="card">
                                                                                <div class="card-body" style="width: 70%; alignment: center">
                                                                                    <canvas id="tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac${apiCallReport.id}_ass_executionStatusChart"
                                                                                            style="alignment: center"></canvas>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="row">
                                                                        <div class="col-sm-8">
                                                                            <h3 style="color: #3E2723">Assertions Execution Result
                                                                                Summery</h3>
                                                                            <table class="table table-striped">
                                                                                <thead>
                                                                                <tr>
                                                                                    <th>Result</th>
                                                                                    <th>Count</th>
                                                                                    <th>Percentage</th>
                                                                                </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                <tr>
                                                                                    <td>Pass</td>
                                                                                    <td>14</td>
                                                                                    <td>70%</td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td>Failed</td>
                                                                                    <td>6</td>
                                                                                    <td>30%</td>
                                                                                </tr>
                                                                                </tbody>
                                                                            </table>
                                                                        </div>
                                                                        <div class="col-sm-4">
                                                                            <div class="card">
                                                                                <div class="card-body" style="width: 70%; alignment: center">
                                                                                    <canvas id="tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac${apiCallReport.id}_ass_executionResultChart"
                                                                                            style="alignment: center"></canvas>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                    <div class="row-space">
                                                                    </div>

                                                                    <div class="row">
                                                                        <div class="col-sm-8">
                                                                            <h3 style="color: #3E2723">Assertions Execution Status
                                                                                Summery</h3>
                                                                            <table class="table table-striped">
                                                                                <thead>
                                                                                <tr>
                                                                                    <th>Key</th>
                                                                                    <th>Value</th>
                                                                                </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                <tr>
                                                                                    <td>HTTP Metod</td>
                                                                                    <td>POST</td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td>URL</td>
                                                                                    <td>http://localhost:8080/account-api/service/accounts</td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td>Request Body</td>
                                                                                    <td>
                                                                                        {"settings":[{"value":"1577641690","key":"setting1"},{"value":"1577641690","key":"setting2"}],"versionId":"1.0.0","ownerName":"Hasitha-1577641690","createdBy":"12345","accountName":"Sathara-1577641690","enterpriseId":"1","parentId":"1","status":"ACTIVE"}
                                                                                    </td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td>Header List</td>
                                                                                    <td>[accept:application/json], [content-type:application/json]
                                                                                    </td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td>Execution time</td>
                                                                                    <td>150ms</td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td>HTTP status code</td>
                                                                                    <td>201</td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td>Response</td>
                                                                                    <td>
                                                                                        {"status":"ACTIVE","createdBy":"12345","accountId":"801","parentId":"1","enterpriseId":"1","accountName":"Sathara-1577641690","ownerName":"Hasitha-1577641690","versionId":"1.0.0","addresses":[],"settings":[{"createdTimestamp":1586636880409,"updatedTimestamp":0,"id":"1552","key":"setting1","value":"1577641690","settingStatus":"ACTIVE","accountId":"801"},{"createdTimestamp":1586636880409,"updatedTimestamp":0,"id":"1553","key":"setting2","value":"1577641690","settingStatus":"ACTIVE","accountId":"801"}]}
                                                                                    </td>
                                                                                </tr>
                                                                                </tbody>
                                                                            </table>
                                                                        </div>
                                                                    </div>

                                                                </div>
                                                            </div>
                                                        </div>
                                             </#list>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                        </#list>
                                    </div>
                                </div>
                            </div>
                        </div>
            </#list>
                    </div>
                </div>
            </div>
        </div>
        </#list>
    </div>
</div>
</body>
</html>
