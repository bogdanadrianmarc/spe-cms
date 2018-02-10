


const selections = {
  template: `
  <div id = "selections-page">
    <h1>Project selection</h1>
    <p>
    Sort projects you would like to work on in your preferred order.<br />
    The first three projects will have the highest priority, but all other submitted choices will be taken into account as well.
    </p>
    <ol id="items">
      <li v-for = "selection in selections" v-bind:key = "selection.priority">
        {{ selection.title }}
        <button  v-on:click = "removeSelection(selection.id)"><i class="fa fa-times" aria-hidden="true"></i></button>
      </li>
    </ol>
    <router-link to = "/projects">
      <div class = "add">
        <i class="fa fa-plus" aria-hidden="true"></i>
      </div>
    </router-link>
  </div>`,
  data: function () {
    return {
      selections: []
    }
  },
  created: function(){
    let self = this;
    $.ajax({
      url: 'http://localhost:8080/selections_id',
      method: 'POST',
      data: {
        id: "test_student",
        login_token: "whvwbvwxghqw!whvwbvwxghqw"
      },
      success: function (dataSelections) {
        console.log("DataS:" + dataSelections);
        $.ajax({
          url: 'http://localhost:8080/projects',
          method: 'POST',
          data: {
            login_token: "whvwbvwxghqw!whvwbvwxghqw"
          },
          success: function (dataProjects) {
            self.selections = dataSelections.map(select => dataProjects[select.projectId]);
          },
          error: function (error) {
            console.log(error);
          }
        });
      },
      error: function (error) {
        console.log(error);
      }
    });

  },
  mounted: function(){
    var el = document.getElementById('items');
    let self = this;
    var sortable = Sortable.create(el,{
      animation: 0,
      sort: true,
      onEnd: function (/**Event*/evt) {
        var itemEl = evt.item;  // dragged HTMLElement
        self.selections = [...evt.to.children].map(element => {
          return element.innerHTML;
        });
      },
    });
  },
  beforeRouteLeave (to, from, next) {
    console.log(this.selections);
    next();
  },
  methods: {
    removeSelection: function(id){
      this.selections = this.selections.filter(function(project){
        if(project.id !== id){
          return project;
        }
      });
      let self = this;
      $.ajax({
        url: 'http://localhost:8080/selection_delete',
        method: 'POST',
        data: {
          studentId: "test_student",
          // -1 accounts for difference in zero-indexing on backend and one-indexing on frontend
          projectId: id-1,
          login_token: "whvwbvwxghqw!whvwbvwxghqw"
        },
        success: function (data) {
          console.log(data);
        },
        error: function (error) {
          console.log(error);
        }
      });
    }
  }
};
