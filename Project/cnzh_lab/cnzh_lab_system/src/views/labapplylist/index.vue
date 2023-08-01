<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input style="width: 200px;" class="filter-item" placeholder="实验室名称">
      </el-input>
      <el-input style="width: 150px;" class="filter-item" placeholder="门牌号">
      </el-input>
      <el-select clearable style="width: 400px" class="filter-item" placeholder="实验室类型">
        <el-option>
        </el-option>
      </el-select>
      <el-input style="width: 100px;" class="filter-item" placeholder="时间段开始">
      </el-input>
      <el-input style="width: 100px;" class="filter-item" placeholder="时间段结束">
      </el-input>
      <el-select style="width: 140px" class="filter-item" placeholder="审核状态">
        <el-option>
        </el-option>
      </el-select>
      <el-button class="filter-item" type="primary" v-waves icon="el-icon-search">检索</el-button>
    </div>
    <div class="operation-container">
      <router-link to="/dashboard/certification_info">
        <el-button class="filter-item" type="primary" v-waves icon="el-icon-document">查看</el-button>
      </router-link>
      <router-link to="/dashboard/certification_handle">
        <el-button class="filter-item" type="primary" v-waves icon="el-icon-edit-outline">申请</el-button>
      </router-link>
    </div>
    <!-- table -->
    <el-table :data="list" class="table-container" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column align="center" prop="business_index" label='序号' width="50">
      </el-table-column>
      <el-table-column label="实验室编号" prop="business_number" width="220">
      </el-table-column>
      <el-table-column label="门牌号" prop="car_number" width="110" align="center">
      </el-table-column>
      <el-table-column label="实验室类型" prop="car_type" align="center">
      </el-table-column>
      <el-table-column class-name="status-col" label="时间段开始/时间段结束" prop="car_master" width="320" align="center">
      </el-table-column>
      <el-table-column class-name="status-col" label="审核人" prop="appraiser" width="110" align="center">
      </el-table-column>
      <el-table-column align="center" label="受理日期" prop="business_data" width="200">
      </el-table-column>
      <el-table-column class-name="status-col" label="审核状态" prop="status" width="150" align="center">
      </el-table-column>
    </el-table>
    <div class="pagination-container">
      <!-- <el-pagination background layout="total, sizes, prev, pager, next, jumper">
      </el-pagination> -->
      <el-pagination
        background
        layout="total, prev, pager, next, jumper"
        :total="100">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: 'labapplylist',
  data () {
    return {
      list: [
        {
          business_index: 1,
          business_number: "HZMVA-RZB-20180101",
          car_number: "N2305",
          car_type: "开放实验室",
          car_master: "1-2节课",
          appraiser: "张三",
          business_data: "2018-01-01 14:23",
          status: "等待审核"
        },
        {
          business_index: 2,
          business_number: "HZMVA-RZB-20180102",
          car_number: "N2305",
          car_type: "开放实验室",
          car_master: "3-4节课",
          appraiser: "张三",
          business_data: "2018-01-01 17:23",
          status: "等待审核"
        },
        {
          business_index: 3,
          business_number: "HZMVA-RZB-20180103",
          car_number: "N2305",
          car_type: "开放实验室",
          car_master: "5-6节课",
          appraiser: "张三",
          business_data: "2018-01-01 11:53",
          status: "等待审核"
        },
        {
          business_index: 4,
          business_number: "HZMVA-RZB-20180104",
          car_number: "N2305",
          car_type: "开放实验室",
          car_master: "7-8节课",
          appraiser: "张三",
          business_data: "2018-01-01 8:23",
          status: "等待审核"
        },
        {
          business_index: 5,
          business_number: "HZMVA-RZB-20180105",
          car_number: "N2306",
          car_type: "科研实验室",
          car_master: "9-10节课",
          appraiser: "张三",
          business_data: "2018-01-01 10:23",
          status: "等待审核"
        },
        {
          business_index: 6,
          business_number: "HZMVA-RZB-20180106",
          car_number: "N2306",
          car_type: "科研实验室",
          car_master: "1-2节课",
          appraiser: "张三",
          business_data: "2018-01-01 11:03",
          status: "等待审核"
        },
        {
          business_index: 7,
          business_number: "HZMVA-RZB-20180107",
          car_number: "N2306",
          car_type: "科研实验室",
          car_master: "3-4节课",
          appraiser: "张三",
          business_data: "2018-01-01 11:23",
          status: "等待审核"
        },
        {
          business_index: 8,
          business_number: "HZMVA-RZB-20180108",
          car_number: "N2306",
          car_type: "科研实验室",
          car_master: "5-6节课",
          appraiser: "张三",
          business_data: "2018-01-01 12:23",
          status: "等待审核"
        },
        {
          business_index: 9,
          business_number: "HZMVA-RZB-20180109",
          car_number: "N2306",
          car_type: "科研实验室",
          car_master: "7-8节课",
          appraiser: "张三",
          business_data: "2018-01-01 15:23",
          status: "等待审核"
        },
        {
          business_index: 10,
          business_number: "HZMVA-RZB-20180110",
          car_number: "N2306",
          car_type: "科研实验室",
          car_master: "9-10节课",
          appraiser: "张三",
          business_data: "2018-01-01 18:23",
          status: "等待审核"
        }
      ]
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.app-container {
  .operation-container {
  margin: 10px;
  }
  .table-container {
    margin-bottom: 10px;
  }
  .pagination-container {
    text-align: center;
  }
}
</style>
