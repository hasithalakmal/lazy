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

        pre {
            white-space: pre-wrap;       /* Since CSS 2.1 */
            white-space: -moz-pre-wrap;  /* Mozilla, since 1999 */
            white-space: -pre-wrap;      /* Opera 4-6 */
            white-space: -o-pre-wrap;    /* Opera 7 */
            word-wrap: break-word;       /* Internet Explorer 5.5+ */
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
                        data: [${report.totalExecutedTestScenariosCount}, ${report.totalSkippedTestScenariosCount}, ${report.totalFailedTestScenariosCount}, ${report.totalInvalidTestScenariosCount}]
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
                        data: [${report.passTestScenariosCount}, ${report.notPassTestSuitesCount}]
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
                        data: [${report.passTestCasesCount}, ${report.notPassTestCasesCount}]
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
                        data: [${testSuiteReport.passTestScenariosCount}, ${testSuiteReport.notPassTestScenariosCount}]
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
                        data: [${testSuiteReport.totalExecutedTestCasesCount}, ${testSuiteReport.totalSkippedTestCasesCount}, ${testSuiteReport.totalFailedTestCasesCount}, ${testSuiteReport.totalInvalidTestCasesCount}]
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
                        data: [${testSuiteReport.passTestCasesCount}, ${testSuiteReport.notPassTestCasesCount}]
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
                        data: [${testSuiteReport.totalExecutedApiCallCount}, ${testSuiteReport.totalSkippedApiCallCount}, ${testSuiteReport.totalFailedApiCallCount}, ${testSuiteReport.totalInvalidApiCallCount}]
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
                        data: [${testSuiteReport.passApiCallCount}, ${testSuiteReport.notPassApiCallCount}]
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
                        data: [${testScenarioReport.totalExecutedTestCasesCount}, ${testScenarioReport.totalSkippedTestCasesCount}, ${testScenarioReport.totalFailedTestCasesCount}, ${testScenarioReport.totalInvalidTestCasesCount}]
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
                        data: [${testScenarioReport.passTestCasesCount}, ${testScenarioReport.notPassTestCasesCount}]
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
                        data: [${testScenarioReport.totalExecutedApiCallCount}, ${testScenarioReport.totalSkippedApiCallCount}, ${testScenarioReport.totalFailedApiCallCount}, ${testScenarioReport.totalInvalidApiCallCount}]
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
                        data: [${testScenarioReport.passApiCallCount}, ${testScenarioReport.notPassApiCallCount}]
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
                        data: [${testCaseReport.totalExecutedApiCallCount}, ${testCaseReport.totalSkippedApiCallCount}, ${testCaseReport.totalFailedApiCallCount}, ${testCaseReport.totalInvalidApiCallCount}]
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
                        data: [${testCaseReport.passApiCallCount}, ${testCaseReport.notPassApiCallCount}]
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
                        data: [${apiCallReport.totalExecutedAssertionsCount}, ${apiCallReport.totalSkippedAssertionsCount}, ${apiCallReport.totalFailedAssertionsCount}, ${apiCallReport.totalInvalidAssertionsCount}]
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
                        data: [${apiCallReport.passAssertionsCount}, ${apiCallReport.notPassAssertionsCount}]
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
                            <td>${report.skippedTestCasesPercentage}%</td>
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
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#testSuiteData"
                           href="#testSuiteCollapse${testSuiteReport.id}"
                           aria-expanded="false" aria-controls="testSuiteCollapse${testSuiteReport.id}">
                            [Test Suite] ${testSuiteReport.testSuiteName} (${testSuiteReport.passTestScenariosCount}/${testSuiteReport
                            .totalTestScenariosCount})
                        </a>
                    </h4>
                </div>
                <div id="testSuiteCollapse${testSuiteReport.id}" class="panel-collapse collapse" role="tabpanel"
                     aria-labelledby="testSuiteHeading${testSuiteReport.id}">
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
                                                <td>${testSuiteReport.passTestScenariosCount}</td>
                                                <td>${testSuiteReport.passTestScenariosPercentage}%</td>
                                            </tr>
                                            <tr>
                                                <td>Failed</td>
                                                <td>${testSuiteReport.notPassTestScenariosCount}</td>
                                                <td>${testSuiteReport.notPassTestScenariosPercentage}%</td>
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
                                                <td>${testSuiteReport.totalExecutedTestCasesCount}</td>
                                                <td>${testSuiteReport.executedTestCasesPercentage}%</td>
                                            </tr>
                                            <tr>
                                                <td>Skipped</td>
                                                <td>${testSuiteReport.totalSkippedTestCasesCount}</td>
                                                <td>${testSuiteReport.skippedTestCasesPercentage}%</td>
                                            </tr>
                                            <tr>
                                                <td>Failed</td>
                                                <td>${testSuiteReport.totalFailedTestCasesCount}</td>
                                                <td>${testSuiteReport.failedTestCasesPercentage}%</td>
                                            </tr>
                                            <tr>
                                                <td>Invalid</td>
                                                <td>${testSuiteReport.totalInvalidTestCasesCount}</td>
                                                <td>${testSuiteReport.invalidTestCasesPercentage}%</td>
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
                                                <td>${testSuiteReport.passTestCasesCount}</td>
                                                <td>${testSuiteReport.passTestCasesPercentage}%</td>
                                            </tr>
                                            <tr>
                                                <td>Failed</td>
                                                <td>${testSuiteReport.notPassTestCasesCount}</td>
                                                <td>${testSuiteReport.notPassTestCasesPercentage}%</td>
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
                                                <td>${testSuiteReport.totalExecutedApiCallCount}</td>
                                                <td>${testSuiteReport.executedApiCallPercentage}%</td>
                                            </tr>
                                            <tr>
                                                <td>Skipped</td>
                                                <td>${testSuiteReport.totalSkippedApiCallCount}</td>
                                                <td>${testSuiteReport.skippedApiCallPercentage}%</td>
                                            </tr>
                                            <tr>
                                                <td>Failed</td>
                                                <td>${testSuiteReport.totalFailedApiCallCount}</td>
                                                <td>${testSuiteReport.failedApiCallPercentage}%</td>
                                            </tr>
                                            <tr>
                                                <td>Invalid</td>
                                                <td>${testSuiteReport.totalInvalidApiCallCount}</td>
                                                <td>${testSuiteReport.invalidApiCallPercentage}%</td>
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
                                                <td>${testSuiteReport.passApiCallCount}</td>
                                                <td>${testSuiteReport.passApiCallPercentage}%</td>
                                            </tr>
                                            <tr>
                                                <td>Failed</td>
                                                <td>${testSuiteReport.notPassApiCallCount}</td>
                                                <td>${testSuiteReport.notPassApiCallPercentage}%</td>
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
                                    <div class="panel-heading" role="tab" id="testScenarioHeading${testScenarioReport.id}"
                                         style="background-color: #90CAF9">
                                        <h4 class="panel-title">
                                            <a class="collapsed" role="button" data-toggle="collapse"
                                               data-parent="#testScenarioData${testSuiteReport.id}"
                                               href="#testScenarioCollapse${testScenarioReport.id}" aria-expanded="false"
                                               aria-controls="testSuiteCollapse${testScenarioReport.id}">
                                                [Test Scenario] ${testScenarioReport.testScenarioName} (${testScenarioReport.passTestCasesCount}
                                                /${testScenarioReport.totalTestCasesCount})
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
                                                                <strong>Total Test Case ${testScenarioReport.totalTestCasesCount}</strong>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-4 ">
                                                            <div class="alert alert-success">
                                                                <strong>Pass Test Case ${testScenarioReport.passTestCasesCount}</strong>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-4 ">
                                                            <div class="alert alert-danger">
                                                                <strong>Failed Test Case ${testScenarioReport.notPassTestCasesCount}</strong>
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
                                                                    <td>${testScenarioReport.totalExecutedTestCasesCount}</td>
                                                                    <td>${testScenarioReport.executedTestCasesPercentage}%</td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Skipped</td>
                                                                    <td>${testScenarioReport.totalSkippedTestCasesCount}</td>
                                                                    <td>${testScenarioReport.skippedTestCasesPercentage}%</td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Failed</td>
                                                                    <td>${testScenarioReport.totalFailedTestCasesCount}</td>
                                                                    <td>${testScenarioReport.failedTestCasesPercentage}%</td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Invalid</td>
                                                                    <td>${testScenarioReport.totalInvalidTestCasesCount}</td>
                                                                    <td>${testScenarioReport.invalidTestCasesPercentage}%</td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <div class="card">
                                                                <div class="card-body" style="width: 70%; alignment: center">
                                                                    <canvas id="tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc_executionStatusChart"
                                                                            style="alignment: center"></canvas>
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
                                                                    <td>${testScenarioReport.passTestCasesCount}</td>
                                                                    <td>${testScenarioReport.passTestCasesPercentage}%</td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Failed</td>
                                                                    <td>${testScenarioReport.notPassTestCasesCount}</td>
                                                                    <td>${testScenarioReport.notPassTestCasesPercentage}%</td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <div class="card">
                                                                <div class="card-body" style="width: 70%; alignment: center">
                                                                    <canvas id="tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc_executionResultChart"
                                                                            style="alignment: center"></canvas>
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
                                                                    <td>${testScenarioReport.totalExecutedApiCallCount}</td>
                                                                    <td>${testScenarioReport.executedApiCallPercentage}%</td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Skipped</td>
                                                                    <td>${testScenarioReport.totalSkippedApiCallCount}</td>
                                                                    <td>${testScenarioReport.skippedApiCallPercentage}%</td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Failed</td>
                                                                    <td>${testScenarioReport.totalFailedApiCallCount}</td>
                                                                    <td>${testScenarioReport.failedApiCallPercentage}%</td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Invalid</td>
                                                                    <td>${testScenarioReport.totalInvalidApiCallCount}</td>
                                                                    <td>${testScenarioReport.invalidApiCallPercentage}%</td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <div class="card">
                                                                <div class="card-body" style="width: 70%; alignment: center">
                                                                    <canvas id="tsu${testSuiteReport.id}_ts${testScenarioReport.id}_ac_executionStatusChart"
                                                                            style="alignment: center"></canvas>
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
                                                                    <td>${testScenarioReport.passApiCallCount}</td>
                                                                    <td>${testScenarioReport.passApiCallPercentage}%</td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Failed</td>
                                                                    <td>${testScenarioReport.notPassApiCallCount}</td>
                                                                    <td>${testScenarioReport.notPassApiCallPercentage}%</td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <div class="card">
                                                                <div class="card-body" style="width: 70%; alignment: center">
                                                                    <canvas id="tsu${testSuiteReport.id}_ts${testScenarioReport.id}_ac_executionResultChart"
                                                                            style="alignment: center"></canvas>
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
                                                                    [Test Case] ${testCaseReport.testCaseName} (${testCaseReport.passApiCallCount}
                                                                    /${testCaseReport.totalApiCallCount})
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
                                                                                    <strong>Total Api
                                                                                        Call ${testCaseReport.totalApiCallCount}</strong>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-sm-4 ">
                                                                                <div class="alert alert-success">
                                                                                    <strong>Pass Api Call ${testCaseReport.passApiCallCount}</strong>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-sm-4 ">
                                                                                <div class="alert alert-danger">
                                                                                    <strong>Failed Api
                                                                                        Call ${testCaseReport.notPassApiCallCount}</strong>
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
                                                                                        <td>${testCaseReport.totalExecutedApiCallCount}</td>
                                                                                        <td>${testCaseReport.executedApiCallPercentage}%</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>Skipped</td>
                                                                                        <td>${testCaseReport.totalSkippedApiCallCount}</td>
                                                                                        <td>${testCaseReport.skippedApiCallPercentage}%</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>Failed</td>
                                                                                        <td>${testCaseReport.totalFailedApiCallCount}</td>
                                                                                        <td>${testCaseReport.failedApiCallPercentage}%</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>Invalid</td>
                                                                                        <td>${testCaseReport.totalInvalidApiCallCount}</td>
                                                                                        <td>${testCaseReport.invalidApiCallPercentage}%</td>
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
                                                                                        <td>${testCaseReport.passApiCallCount}</td>
                                                                                        <td>${testCaseReport.passApiCallPercentage}%</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>Failed</td>
                                                                                        <td>${testCaseReport.notPassApiCallCount}</td>
                                                                                        <td>${testCaseReport.notPassApiCallPercentage}%</td>
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
                                                                            <div class="panel-heading" role="tab"
                                                                                 id="apiCallHeading${apiCallReport.id}"
                                                                                 style="background-color: #E3F2FD">
                                                                                <h4 class="panel-title">
                                                                                    <a class="collapsed" role="button" data-toggle="collapse"
                                                                                       data-parent="#apiCallData_${testSuiteReport.id}_${testScenarioReport
                                                                                       .id}_${testCaseReport.id}"
                                                                                       href="#apiCallCollapse${apiCallReport.id}"
                                                                                       aria-expanded="false"
                                                                                       aria-controls="testSuiteCollapse${apiCallReport.id}">
                                                                                        [Api Call] ${apiCallReport.name}
                                                                                        (${apiCallReport.passAssertionsCount}
                                                                                        /${apiCallReport.totalAssertionsCount})
                                                                                    </a>
                                                                                </h4>
                                                                            </div>
                                                                            <div id="apiCallCollapse${apiCallReport.id}"
                                                                                 class="panel-collapse collapse" role="tabpanel"
                                                                                 aria-labelledby="apiCallHeading${apiCallReport.id}">
                                                                                <div class="panel-body">

                                                                                    <div class="row">
                                                                                        <div class="col-sm-4 ">
                                                                                            <div class="alert alert-info">
                                                                                                <strong>Total
                                                                                                    Assertions ${apiCallReport.totalAssertionsCount}</strong>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="col-sm-4 ">
                                                                                            <div class="alert alert-success">
                                                                                                <strong>Pass
                                                                                                    Assertions ${apiCallReport.passAssertionsCount}</strong>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="col-sm-4 ">
                                                                                            <div class="alert alert-danger">
                                                                                                <strong>Failed
                                                                                                    Assertions ${apiCallReport.notPassAssertionsCount}</strong>
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
                                                                                                    <td>${apiCallReport.totalExecutedAssertionsCount}</td>
                                                                                                    <td>${apiCallReport.executedAssertionsPercentage}
                                                                                                        %
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td>Skipped</td>
                                                                                                    <td>${apiCallReport.totalSkippedAssertionsCount}</td>
                                                                                                    <td>${apiCallReport.skippedAssertionsPercentage}
                                                                                                        %
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td>Failed</td>
                                                                                                    <td>${apiCallReport.totalFailedAssertionsCount}</td>
                                                                                                    <td>${apiCallReport.failedAssertionsPercentage}%
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td>Invalid</td>
                                                                                                    <td>${apiCallReport.totalInvalidAssertionsCount}</td>
                                                                                                    <td>${apiCallReport.invalidAssertionsPercentage}
                                                                                                        %
                                                                                                    </td>
                                                                                                </tr>
                                                                                                </tbody>
                                                                                            </table>
                                                                                        </div>
                                                                                        <div class="col-sm-4">
                                                                                            <div class="card">
                                                                                                <div class="card-body"
                                                                                                     style="width: 70%; alignment: center">
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
                                                                                                    <td>${apiCallReport.passAssertionsCount}</td>
                                                                                                    <td>${apiCallReport.passAssertionsPercentage}%
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td>Failed</td>
                                                                                                    <td>${apiCallReport.notPassAssertionsCount}</td>
                                                                                                    <td>${apiCallReport.notPassAssertionsPercentage}
                                                                                                        %
                                                                                                    </td>
                                                                                                </tr>
                                                                                                </tbody>
                                                                                            </table>
                                                                                        </div>
                                                                                        <div class="col-sm-4">
                                                                                            <div class="card">
                                                                                                <div class="card-body"
                                                                                                     style="width: 70%; alignment: center">
                                                                                                    <canvas id="tsu${testSuiteReport.id}_ts${testScenarioReport.id}_tc${testCaseReport.id}_ac${apiCallReport.id}_ass_executionResultChart"
                                                                                                            style="alignment: center"></canvas>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>

                                                                                    <div class="row-space">
                                                                                    </div>

                                                                                    <div class="row">
                                                                                        <div class="col-sm-12">
                                                                                            <h3 style="color: #3E2723">Request/Response Details</h3>
                                                                                            <table class="table table-striped">
                                                                                                <thead>
                                                                                                <tr>
                                                                                                    <th>Key</th>
                                                                                                    <th>Value</th>
                                                                                                </tr>
                                                                                                </thead>
                                                                                                <tbody>
                                                                                                <#if apiCallReport.httpMethod?has_content>
                                                                                                <tr>
                                                                                                    <td>HTTP Metod</td>
                                                                                                    <td>${apiCallReport.httpMethod}</td>
                                                                                                </tr>
                                                                                                </#if>
                                                                                                <#if apiCallReport.url?has_content>
                                                                                                <tr>
                                                                                                    <td>URL</td>
                                                                                                    <td>${apiCallReport.url}</td>
                                                                                                </tr>
                                                                                                </#if>
                                                                                                <#if apiCallReport.requestBody?has_content>
                                                                                                    <tr>
                                                                                                        <td>Request Body</td>
                                                                                                        <td>
                                                                                                            ${apiCallReport.requestBody}
                                                                                                        </td>
                                                                                                    </tr>
                                                                                                </#if>
                                                                                                <#if apiCallReport.headers?has_content>
                                                                                                <tr>
                                                                                                    <td>Header List</td>
                                                                                                    <td>
                                                                                                        ${apiCallReport.headers}
                                                                                                    </td>
                                                                                                </tr>
                                                                                                </#if>
                                                                                                <#if apiCallReport.executionTime?has_content>
                                                                                                <tr>
                                                                                                    <td>Execution time</td>
                                                                                                    <td>${apiCallReport.executionTime}</td>
                                                                                                </tr>
                                                                                                 </#if>
                                                                                                <#if apiCallReport.httpStatusCode?has_content>
                                                                                                <tr>
                                                                                                    <td>HTTP status code</td>
                                                                                                    <td>${apiCallReport.httpStatusCode}</td>
                                                                                                </tr>
                                                                                                </#if>
                                                                                                <#if apiCallReport.response?has_content>
                                                                                                    <tr>
                                                                                                        <td>Response</td>
                                                                                                        <td >
                                                                                                            <div overflow>
                                                                                                                <pre>${apiCallReport.response}</pre>
                                                                                                            </div>
                                                                                                        </td>
                                                                                                    </tr>
                                                                                                <#else>
                                                                                                    <tr>
                                                                                                        <td>Response</td>
                                                                                                        <td >
                                                                                                            No Response Found due to API. It might
                                                                                                            be due to a connectivity issue. Please
                                                                                                            check the configurations and
                                                                                                            application status.
                                                                                                        </td>
                                                                                                    </tr>
                                                                                                </#if>
                                                                                                </tbody>
                                                                                            </table>
                                                                                        </div>
                                                                                    </div>

                                                                                    <div class="row">
                                                                                        <div class="col-sm-12">
                                                                                            <h3 style="color: #3E2723">Assertion Results</h3>
                                                                                            <table class="table table-striped">
                                                                                                <thead>
                                                                                                <tr>
                                                                                                    <th>Id</th>
                                                                                                    <th>Name</th>
                                                                                                    <th>Pass</th>
                                                                                                    <th>Status</th>
                                                                                                </tr>
                                                                                                </thead>
                                                                                                <tbody>
                                                                                                <#list apiCallReport.assertions as assertion>
                                                                                                    <tr>
                                                                                                        <td>${assertion.id}</td>
                                                                                                        <td>${assertion.name}</td>
                                                                                                        <td>${assertion.isPass}</td>
                                                                                                        <td>${assertion.status}</td>
                                                                                                        <td>${assertion.notes}</td>
                                                                                                    </tr>
                                                                                                </#list>
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
