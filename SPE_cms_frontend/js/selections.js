


const selections = {
  template: `

  <div id = "selections-page">
  <h1>Project selection</h1>
  <p>
  Sort projects you would like to work on in your preferred order.<br />
  The first three projects will have the highest priority, but all other submitted choices will be taken into account as well.
  </p>
  <ol id="items">
  <transition-group name="slide-fade">
  <li v-for = "selection in selections" v-bind:key = "selection.priority" :id ="selection.id">
  {{ selection.title }}
  <button  v-on:click = "removeSelection(selection.id)"><i class="fa fa-times" aria-hidden="true"></i></button>
  </li>
  </transition-group>
  </ol>
  <router-link to = "/projects">
  <div class = "add">
  <i class="fa fa-plus" aria-hidden="true"></i>
  </div>
  </router-link>
  </div>`
  ,
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
        $.ajax({
          url: 'http://localhost:8080/projects',
          method: 'POST',
          data: {
            login_token: "whvwbvwxghqw!whvwbvwxghqw"
          },
          success: function (dataProjects) {
            // sort based on priority
            dataSelections = dataSelections.sort(function(a, b){
              return a.priority - b.priority;
            });
            // map project ids to actual project objects
            self.selections = dataSelections.map(select => {
              let returnObj = dataProjects[select.projectId];
              returnObj.priority = dataSelections.filter(obj => obj.projectId === select.projectId)[0].priority;
              return returnObj;
            });
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
      onEnd: function (evt) {
        console.log("ProjectID:" + (evt.item.attributes.id.value - 1) + " Old:" + evt.oldIndex + " New:" + evt.newIndex);
        $.ajax({
          url: 'http://localhost:8080/selection_update',
          method: 'POST',
          data: {
            studentId: "test_student",
            projectId: evt.item.attributes.id.value - 1,
            login_token: "whvwbvwxghqw!whvwbvwxghqw",
            oldPriority: evt.oldIndex + 1,
            newPriority: evt.newIndex + 1
          },
          success: function (data) {
            console.log(data);
          },
          error: function (error) {
            console.log(error);
          }
        });
        // swap on display
        // const old = evt.oldIndex;
        // const nou = evt.newIndex;
        // let oldBackup = self.selections[old];
        // let newBackup = self.selections[nou];
        // self.selections[nou] = oldBackup;
        // self.selections[old] = newBackup;
        // swap on backend
        // $.ajax({
        //   url: 'http://localhost:8080/selection_delete',
        //   method: 'POST',
        //   data: {
        //     studentId: "test_student",
        //     // -1 accounts for difference in zero-indexing on backend and one-indexing on frontend
        //     projectId: oldBackup.id,
        //     login_token: "whvwbvwxghqw!whvwbvwxghqw"
        //   },
        //   success: function (data) {
        //     console.log(data);
        //   },
        //   error: function (error) {
        //     console.log(error);
        //   }
        // });
        // $.ajax({
        //   url: 'http://localhost:8080/selection_delete',
        //   method: 'POST',
        //   data: {
        //     studentId: "test_student",
        //     // -1 accounts for difference in zero-indexing on backend and one-indexing on frontend
        //     projectId: newBackup.id,
        //     login_token: "whvwbvwxghqw!whvwbvwxghqw"
        //   },
        //   success: function (data) {
        //     console.log(data);
        //   },
        //   error: function (error) {
        //     console.log(error);
        //   }
        // });
        //
        // $.ajax({
        //   url: 'http://localhost:8080/selection_save',
        //   method: 'POST',
        //   data: {
        //     studentId: "test_student",
        //     // -1 accounts for difference in zero-indexing on backend and one-indexing on frontend
        //     projectId: oldBackup.id,
        //     priority: newBackup.priority,
        //     login_token: "whvwbvwxghqw!whvwbvwxghqw"
        //   },
        //   success: function (data) {
        //     console.log(data);
        //   },
        //   error: function (error) {
        //     console.log(error);
        //   }
        // });
        // $.ajax({
        //   url: 'http://localhost:8080/selection_save',
        //   method: 'POST',
        //   data: {
        //     studentId: "test_student",
        //     // -1 accounts for difference in zero-indexing on backend and one-indexing on frontend
        //     projectId: newBackup.id,
        //     priority: oldBackup.priority,
        //     login_token: "whvwbvwxghqw!whvwbvwxghqw"
        //   },
        //   success: function (data) {
        //     console.log(data);
        //   },
        //   error: function (error) {
        //     console.log(error);
        //   }
        // });
      },
    });
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
