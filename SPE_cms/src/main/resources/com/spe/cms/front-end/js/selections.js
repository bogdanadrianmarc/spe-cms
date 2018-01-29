


const selections = {
  template: `
  <div id = "selections-page">
  <h1>Project selection order</h1>
  <h2>Drag and drop items in the order that you like.</h2>
  <ol id="items">
  <li>'The rock outside MVB' inc.</li>
  <li>Green Foundation</li>
  <li>Oracle Waiter</li>
  <li>IBM Server Monitoring</li>
  <li>University of Bristol RFID Laptops</li>
  <li>Sailing Society Dashboard</li>
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
