<template>
  <div class="charts-container">
    <div class="charts-section-up">
      <div class="charts-up-left">
        <h3>业务占比</h3>
        <ve-pie :data="chartDataPie"></ve-pie>
      </div>
      <div class="charts-up-right">
        <h3>流程状况</h3>
        <ve-histogram :data="chartDataHistogram"></ve-histogram>
      </div>
    </div>
    <div class="charts-section-down">
      <h3>月业务量</h3>
      <ve-line :data="chartData"></ve-line>
    </div>
  </div>
</template>

<script>
import VeLine from 'v-charts/lib/line.common'         //折线图
import VeHistogram from 'v-charts/lib/histogram.common'    //柱形图
import VePie from 'v-charts/lib/pie.common'         //饼图
export default {
  name: 'charts',
  components: { VeLine,VeHistogram,VePie },
  data () {
    return {
      chartDataPie: {
          columns: ['业务', '业务数'],
          rows: [
            { '业务': '认证', '业务数': 53 },
            { '业务': '评估', '业务数': 70 }
          ]
        },
        chartDataHistogram: {
          columns: ['状态', '认证', '评估'],
          rows: [
            { '状态': '待提交', '认证': 23, '评估': 43},
            { '状态': '审核中', '认证': 30, '评估': 40},
            { '状态': '等待生成报告', '认证': 43, '评估': 63},
            { '状态': '已完成', '认证': 23, '评估': 73}
          ]
        },
      chartData: {
            columns: ['日期', '认证', '评估'],
	          rows: [
	            { '日期': '1月', '认证': 15, '评估': 26},
	            { '日期': '2月', '认证': 22, '评估': 35},
	            { '日期': '3月', '认证': 63, '评估': 44},
	            { '日期': '4月', '认证': 24, '评估': 73},
	            { '日期': '5月', '认证': 55, '评估': 42},
              { '日期': '6月', '认证': 46, '评估': 21},
              { '日期': '7月', '认证': 15, '评估': 26},
	            { '日期': '8月', '认证': 22, '评估': 35},
	            { '日期': '9月', '认证': 63, '评估': 44},
	            { '日期': '10月', '认证': 24, '评估': 73},
	            { '日期': '11月', '认证': 55, '评估': 42},
	            { '日期': '12月', '认证': 46, '评估': 21}
	          ]
        }
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.charts-container {
  height: 100%;
  width: 100%;
}
.charts-section-up {
  width: 100%;
  padding: 20px;
  border-bottom: 1 solid #eee;
  .charts-up-left {
    width: 50%;
  }
  .charts-up-right {
    position: relative;
    margin-top: -450px;
    margin-left: 50%;
  }
}
.charts-section-down {
  width: 100%;
  padding: 20px;
  border: 2px solid #eee;
}
</style>
