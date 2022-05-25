<template>
  <div>
    <el-row :gutter="10" style="margin-bottom: 60px">
      <el-col :span="6">
        <el-card style="color: #409eff">
          <div>
            <i class="el-icon-user-solid" style="margin-right: 5px"></i>用户总数
          </div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            100
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #f56c60"
          ><div>
            <i class="el-icon-money" style="margin-right: 5px"></i>销售总额
          </div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            ￥1000000
          </div></el-card
        >
      </el-col>
      <el-col :span="6">
        <el-card style="color: #e6a23c"
          ><div>
            <i class="el-icon-coin" style="margin-right: 5px"></i>收益总额
          </div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            ￥300000
          </div></el-card
        >
      </el-col>
      <el-col :span="6">
        <el-card style="color: #67c23a"
          ><div>
            <i class="el-icon-s-shop" style="margin-right: 5px"></i>门店总数
          </div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            72
          </div></el-card
        >
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="12"
        ><el-card id="main" style="width: 500px; height: 450px"></el-card>
      </el-col>
      <el-col :span="12"
        ><el-card id="pie" style="width: 500px; height: 450px"></el-card
      ></el-col>
    </el-row>
  </div>
</template>

<script>
import * as echarts from "echarts";
export default {
  name: "Home",
  data() {
    return {};
  },
  mounted() {
    // 折线图data start
    var option = {
      title: {
        text: "各季度会员数量统计",
        subtext: "趋势图",
        left: "center",
      },
      tooltip: {
        trigger: "item",
      },
      legend: {
        orient: "vertical",
        left: "left",
      },
      xAxis: {
        type: "category",
        data: ["第一季度", "第二季度", "第三季度", "第四季度"],
      },
      yAxis: {
        type: "value",
      },
      series: [
        {
          name: "蜜雪冰城",
          data: [],
          type: "line",
        },
        {
          name: "茶百道",
          data: [],
          type: "bar",
        },
      ],
    };
    // 折线图data end

    // 饼图data start
    var pieOption = {
      title: {
        text: "各季度会员数量统计",
        subtext: "比例图",
        left: "center",
      },
      tooltip: {
        trigger: "item",
        formatter: "{a} <br/>{b} : {c} ({d}%)",
      },
      legend: {
        orient: "vertical",
        left: "left",
      },
      series: [
        {
          name: "蜜雪冰城",
          type: "pie",
          radius: "50%",
          center: ["25%", "50%"],
          label: {
            //饼图图形上的文本标签
            normal: {
              show: true,
              position: "inner", //标签的位置
              textStyle: {
                fontWeight: 700,
                fontSize: 14, //文字的字体大小
                color: "#fff",
              },
              formatter: "{d}%",
            },
          },
          // 填空
          data: [],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: "rgba(0, 0, 0, 0.5)",
            },
          },
        },
        {
          name: "茶百道",
          type: "pie",
          radius: "55%",
          center: ["75%", "50%"],
          label: {
            //饼图图形上的文本标签
            normal: {
              show: true,
              position: "inner", //标签的位置
              textStyle: {
                fontWeight: 700,
                fontSize: 14, //文字的字体大小
                color: "#fff",
              },
              formatter: "{d}%",
            },
          },
          // 填空
          data: [
            { name: "第一季度", value: 5 },
            { name: "第二季度", value: 6 },
            { name: "第三季度", value: 7 },
            { name: "第四季度", value: 8 },
          ],
        },
      ],
    };
    // 饼图data end

    var chartDom = document.getElementById("main");
    var myChart = echarts.init(chartDom);

    var pieDom = document.getElementById("pie");
    var pieChart = echarts.init(pieDom);

    this.request.get("echarts/members").then((res) => {
      // 填空
      // option.xAxis.data = res.data.x;
      option.series[0].data = res.data;
      option.series[1].data = res.data;
      // 数据准备完毕之后再setOption
      myChart.setOption(option);

      pieOption.series[0].data = [
        { name: "第一季度", value: res.data[0] },
        { name: "第二季度", value: res.data[1] },
        { name: "第三季度", value: res.data[2] },
        { name: "第四季度", value: res.data[3] },
      ];

      pieChart.setOption(pieOption);
    });
  },
};
</script>

<style>
</style>