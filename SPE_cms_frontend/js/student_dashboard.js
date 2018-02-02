const user_dashboard = {
  data: function(){
    return {
      projectList: [],
      selectionList: [],
      loading: true,
      order: [],
      orderIndex: 0
    }
  },
  created: function(){
    let self = this;
    $.ajax({
      url: 'http://localhost:8080/projects',
      method: 'GET',
      success: function (data) {
        let size = data.length;
        let actual = 0;
        self.selectionList = Array(size);
        self.order = Array(size);
        while(size--){
          self.$set(self.selectionList,actual,0);
          actual++;
        }
        self.projectList = data;
      },
      error: function (error) {
        console.log(error);
      }
    });

  },
  mounted: function(){
    let self = this;
    // TODO: Remove this setTimeout, demonstration purpose only
    setTimeout(function(){
      self.loading = false;
    },600);

  },
  methods:{
    clickBTN: function(index){
      console.log(this.selectionList);
      if(this.selectionList[index] == 0){
        this.$set(this.order,this.orderIndex,this.projectList[index]);
        this.orderIndex++;
        this.$set(this.selectionList,index,1);
      }
      else{
        const tbd = this.order.indexOf(this.projectList[index])
        this.order.splice(tbd,1);
        this.order.splice(tbd,0,undefined);
        this.$set(this.selectionList,index,0);
      }
      console.log(this.order);
    },
    isUndefined: function(item){
      return typeof item === "undefined";
    }
  },
  template: `
  <transition name = "fade" mode = "out-in">
    <div v-if = "loading" class = "loader" key="loading">
      <loader_spinner></loader_spinner>
    </div>
    <div v-else key="loaded">
      <div id = "student-dashboard">
        <div id = "fixed-footer">
          <h1>Max Demian</h1>
          <h2 style = "display: inline-block">Ordered preferences: </h2>
          <transition-group name = "food">
            <span v-for="project in order" v-if="!isUndefined(project)"  v-bind:key="project.id">
              <span>#{{project.id}} </span>
            </span>
          </transition-group>
        </div>
        <div id = "condensed-projects">
          <div v-for="(project, index) in projectList" class = "condensed-project">
            <span class = "condensed-project-number">
              Project #{{index+1}}
            </span>
            <span class = "condensed-project-title">
              {{project.title}}
            </span>
            <span class = "condensed-button">
              <button v-if = "selectionList[index]" v-on:click="clickBTN(index)" class = "selected"><i class = "fa fa-minus"></i></button>
              <button v-else v-on:click="clickBTN(index)" ><i class = "fa fa-plus"></i></button>
            </span>
          </div>
        </div>
      </div>
    </div>
  </transition>
  `
};
