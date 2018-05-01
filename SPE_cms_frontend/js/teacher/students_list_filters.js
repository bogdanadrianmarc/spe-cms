Vue.component('students_list_filters_teacher', {
  template: `<div id = "student-filter-bar" style = "width: 65vw;
  margin: 1.25em auto 3em;">
  <h1>Filter Results</h1>
  <span class = "wrapperSearch">
    <input name = "title-input" v-on:input = "this.$parent.sortTitle" type = "text" placeholder = "Student username"></input>
  </span>
</div>`
});
