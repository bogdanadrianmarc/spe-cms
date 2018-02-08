


const selections = {
  template: `
  <div id = "selections-page">
    <h1>Project selection</h1>
    <p>
    Sort projects you would like to work on in your preferred order.<br />
    The first three projects will have the highest priority, but all other submitted choices will be taken into account as well.
    </p>
    <ol id="items">
      <li>
        'The rock outside MVB' inc.
        <button><i class="fa fa-minus" aria-hidden="true"></i></button>
      </li>
      <li>
        Green Foundation
        <button><i class="fa fa-times" aria-hidden="true"></i></button>
      </li>
      <li>
        Oracle Waiter
        <button><i class="fa fa-times" aria-hidden="true"></i></button>
      </li>
      <li>
        IBM Server Monitoring
        <button><i class="fa fa-times" aria-hidden="true"></i></button>
      </li>
      <li>
        University of Bristol RFID Laptops
        <button><i class="fa fa-times" aria-hidden="true"></i></button>
      </li>
      <li>
        Sailing Society Dashboard
        <button><i class="fa fa-times" aria-hidden="true"></i></button>
      </li>
      <li>
        Museum Augmented Reality
        <button><i class="fa fa-times" aria-hidden="true"></i></button>
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
  }
};
