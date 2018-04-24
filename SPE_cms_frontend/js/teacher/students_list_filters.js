Vue.component('students_list_filters_teacher', {
  template: `<div id = "student-filter-bar">
  <h1>Filter Results</h1>
  <span v-if = "this.$parent.sortOrder === -1" class = "wrapperUp"  v-on:click = "this.$parent.sortStudents">
    <button>
      Alphabetic
    </button>
  </span>
  <span v-else class = "wrapperDown"  v-on:click = "this.$parent.sortStudents">
    <button>
      Alphabetic
    </button>
  </span>
  <span class = "wrapperSearch">
    <input name = "title-input" v-on:input = "this.$parent.sortTitle" type = "text" placeholder = "Student username"></input>
  </span>
</div>`
});
