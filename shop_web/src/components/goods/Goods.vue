<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/Home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统菜单</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-alert title="添加商品" type="info" center show-icon></el-alert>
      <el-steps :active="active" finish-status="success" align-center>
        <el-step title="商品信息"></el-step>
        <el-step title="商品图片"></el-step>
        <el-step title="完成"></el-step>
      </el-steps>
      <div class="scrollbar-wrap" :style="{height: scrollHeight}" v-show="active==0">
        <el-form label-width="80px" :model="params">
          <el-form-item label="商品名称">
            <el-input v-model="params.coName" :style="{ width: '20%', margin: '0px' }"></el-input>
          </el-form-item>
          <el-form-item label="商品价格">
            <el-input v-model="params.coPrice" :style="{ width: '20%', margin: '0px' }"></el-input>
          </el-form-item>
          <el-form-item label="商品描述">
            <el-input
              type="textarea"
              v-model="params.coRemark"
              :style="{ width: '20%', margin: '0px' }"
            ></el-input>
          </el-form-item>
        </el-form>
        <!-- 按钮区域 -->
        <div :style="{ marginLeft:'100px' }">
          <el-button size="small" type="primary" @click="btnInfo">提 交</el-button>
          <el-button size="small" type="info">重 置</el-button>
        </div>
      </div>
      <div class="scrollbar-wrap" :style="{height: scrollHeight}" v-show="active==1">
        <el-button size="small" type="info" @click="btnCommit" :style="{ width:'80px' }">上一步</el-button>
        <el-button size="small" type="info" @click="btnToInfo" :style="{ width:'80px' }">上一步</el-button>
        <el-upload
          class="upload-demo"
          action="https://jsonplaceholder.typicode.com/posts/"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :file-list="fileList"
          list-type="picture"
          :style="{ marginTop:'10px' }"
        >
          <el-button size="small" type="primary">上传图片</el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'Goods',
  data() {
    return {
      scrollHeight: '0px',
      //提交的参数
      params: {
        attrsId: '',
        coName: '',
        coPrice: '',
        coRemark: '',
      },
      //步骤
      active: 0,
      fileList: [
        {
          name: 'food.jpeg',
          url:
            'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100',
        },
        {
          name: 'food2.jpeg',
          url:
            'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100',
        },
        {
          name: 'food2.jpeg',
          url:
            'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100',
        },
        {
          name: 'food2.jpeg',
          url:
            'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100',
        },
        {
          name: 'food2.jpeg',
          url:
            'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100',
        },
        {
          name: 'food2.jpeg',
          url:
            'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100',
        },
        {
          name: 'food2.jpeg',
          url:
            'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100',
        },
        {
          name: 'food2.jpeg',
          url:
            'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100',
        },
      ],
    }
  },
  created() {},
  computed: {},
  mounted() {
    this.scrollHeight = window.innerHeight * 0.55 + 'px'
    this.params.attrsId = this.$route.query.data
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList)
    },
    handlePreview(file) {
      console.log(file)
    },
    btnInfo() {
      this.active = 1
    },
    btnToInfo() {
      this.active = 0
    },
  },
}
</script>

<style  scoped>
.el-tabs {
  margin: 15px 0;
}

.el-tag {
  margin: 10px;
}

.input-new-tag {
  width: 120px;
}

.scrollbar-wrap {
  overflow-x: scroll;
  white-space: nowrap;
  margin-top: 20px;
}
</style>