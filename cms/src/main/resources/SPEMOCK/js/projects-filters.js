Vue.component('projects-filters', {
  template: `<div id = "filter-bar">
                <button v-on:click = "this.$parent.sortApplicants">
                  Sort by applicants
                  <span v-if = "this.$parent.sortOrder === -1">&#9660;</span>
                  <span v-else>&#9650;</span>
                </button>
                <input name = "tags-input" v-on:input = "this.$parent.sortTags" type = "text" placeholder = "Tags separated by commas"></input>
            </div>`
});