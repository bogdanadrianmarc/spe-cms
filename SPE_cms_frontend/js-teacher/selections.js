


const selections = {
  template: `
  <div id = "selections-page">
    <h1>Student X's Project selection</h1>
    <p>
    This is sc16913's project selections.<br />
    The first three projects have the highest priority, but all other selections could be taken into account if needed.
    </p>
    <ol id="items">
      <li>
        'The rock outside MVB' inc.
      </li>
      <li>
        Green Foundation
      </li>
      <li>
        Oracle Waiter
      </li>
      <li>
        IBM Server Monitoring
      </li>
      <li>
        University of Bristol RFID Laptops
      </li>
      <li>
        Sailing Society Dashboard
      </li>
      <li>
        Museum Augmented Reality
      </li>
    </ol>
    <router-link to = "/projects">
      <div class = "add">
        <i class = "text">Allocate student to a group</i>
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
        id: 0,
        user: "test_student",
        password: "test_student"
      },
      success: function (dataSelections) {
        $.ajax({
          url: 'http://localhost:8080/projects',
          method: 'POST',
          data: {
            user: "test_student",
            password: "test_student"
          },
          success: function (dataProjects) {
            self.selections = dataSelections.map(select => dataProjects[select.projectId].title);
            console.log(JSON.stringify(self.selections));
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
  beforeRouteLeave (to, from, next) {
    console.log(this.selections);
    next();
  }
};
