


const selections = {
  template: `
  <div id = "selections-page">
    <h1>Project selection order</h1>
    <ol id="items">
      <li>
        'The rock outside MVB' inc.
        <i class="fa fa-arrows" aria-hidden="true"></i>
      </li>
      <li>
        Green Foundation
        <i class="fa fa-arrows" aria-hidden="true"></i>
      </li>
      <li>
        Oracle Waiter
        <i class="fa fa-arrows" aria-hidden="true"></i>
      </li>
      <li>
        IBM Server Monitoring
        <i class="fa fa-arrows" aria-hidden="true"></i>
      </li>
      <li>
        University of Bristol RFID Laptops
        <i class="fa fa-arrows" aria-hidden="true"></i>
      </li>
      <li>
        Sailing Society Dashboard
        <i class="fa fa-arrows" aria-hidden="true"></i>
      </li>
      <li>
        Oracle Waiter
        <i class="fa fa-arrows" aria-hidden="true"></i>
      </li>
      <li>
        IBM Server Monitoring
        <i class="fa fa-arrows" aria-hidden="true"></i>
      </li>
      <li>
        University of Bristol RFID Laptops
        <i class="fa fa-arrows" aria-hidden="true"></i>
      </li>
      <li>
        Sailing Society Dashboard
        <i class="fa fa-arrows" aria-hidden="true"></i>
      </li>
    </ol>
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
