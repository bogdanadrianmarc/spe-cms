Vue.component('projects_list_item_teacher', {
  props: ['projects','priority', 'list'],
  data: function(){

    return {
      tagColors: [],
      times: 0,
    }
  },
  created: function(){
    this.$parent.$parent.$parent.getTagColors(this.projects,this);
    let self = this;
    $.ajax({
      url: 'http://localhost:8080/project_id',
      method: 'POST',
      data: {
        id: this.projects.id,
        login_token: "whvwbwhdfkhu!whvwbwhdfkhu"
      },
      error: function (error) {
        console.log(error);
      }
    });
  },
  methods: {
    clickBTN: function(project, index){
      let self = this;
      var id = this.list.indexOf(project);
      swal({
        title: "Are you sure?",
        text: "Once deleted, you will not be able to recover this project!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      })
      .then((willDelete) => {
        if (willDelete) {
          swal("This project has been unpublished!", {
            icon: "success",
          });
          this.$delete(this.list, id); //-1 because projects start at 0.
          $.ajax({
            url: 'http://localhost:8080/project_delete',
            method: 'POST',
            data: {
              projectId: index,
              login_token: "whvwbwhdfkhu!whvwbwhdfkhu"
            },
            error: function (error) {
              console.log(error);
            }
          });
        } else {
          swal("The project is still in the game!");
        }
      });
    },
    isUndefined: function(item){
      return typeof item === "undefined";
    },
    confirm: function(){
      $.notify.addStyle('foo', {
        html:
          "<div>" +
            "<div class='clearfix'>" +
              "<div class='title' data-notify-html='title'/>" +
              "<div class='buttons'>" +
                "<button class='no'>Cancel</button>" +
                "<button class='yes' data-notify-text='button'></button>" +
              "</div>" +
            "</div>" +
          "</div>"
      });
    },
    listenForClick: function(){
      //listen for click events from this style
      $(document).on('click', '.notifyjs-foo-base .no', function() {
        //programmatically trigger propogating hide event
        $(this).trigger('notify-hide');
      });
      $(document).on('click', '.notifyjs-foo-base .yes', function() {
        //show button text
        alert($(this).text() + " clicked!");
        //hide notification
        $(this).trigger('notify-hide');
      });
    }
  },
  template: `<div class = "project-list-item">
    <img class = "project-list-item-image" alt = "Project Image" v-bind:src="projects.imgUrl" />
    <div class = "project-list-item-content">
    <div class = "tag-container" v-for = "(tag, index) in projects.tags.split(',')">
    <div class = "tag" v-bind:class="tagColors[index]"> {{tag}} </div>
    </div>
    <h1>  {{ projects.title }}  </h1>
    <!-- truncate string -->
    <p> {{ projects.content.substring(0,500) + "..." }} </p>
    <div class = "project-list-item-applicants">
    No. of applicants <span class = "applicants"> {{ projects.applicantsNr }}</span>
    </div>
    <div class = "project-list-item-id">
    Project #{{ projects.id }}
    </div>
    <div style="clear:both"></div>
    <hr />
    <!-- TODO: Discuss in meeting -->
    <div class = "project-list-item-license">
    <img src = "img/mit-license.png" />
    <p>MIT License</p>
    </div>

    <div class = "select-project-btn">
    <button v-on:click="clickBTN(projects, projects.id)">Unpublish project</button>
    </div>

    <router-link v-bind:to="projects.projectUrl">
    </i><div class = "learn-more-btn">Details</div>
    </router-link>
    </div>
  </div>`
});
