Vue.component('projects_list_filters_student', {
  template: `<div id = "filter-bar">
  <h1>Filter Results</h1>
  <span v-if = "this.$parent.sortOrder === -1" class = "wrapperUp"  v-on:click = "this.$parent.sortApplicants">
    <button>
      Applicants
    </button>
  </span>
  <span v-else class = "wrapperDown"  v-on:click = "this.$parent.sortApplicants">
    <button>
      Applicants
    </button>
  </span>
  <span class = "wrapperSearch">
    <input name = "title-input" v-on:input = "this.$parent.sortTitle" type = "text" placeholder = "Project title"></input>
  </span>
  <span class = "wrapperFilter">
    <input name = "tags-input" v-on:input = "this.$parent.sortTags" type = "text" placeholder = "Tags separated by commas"></input>
  </span>
</div>`
});
