


const selections = {
  template: `
  <div id = "selections-page">
  <h1>Project selection order</h1>
  <h2>Drag and drop items in the order that you like.</h2>
    <ul id="items">
      <li>'The rock outside MVB' inc.</li>
      <li>Green Foundation</li>
      <li>Oracle Waiter</li>
      <li>IBM Server Monitoring</li>
      <li>University of Bristol RFID Laptops</li>
      <li>Sailing Society Dashboard</li>
    </ul>
  </div>`,
  data: function () {
  return {
    selections: [

    ]
  }
},
  mounted: function(){
    var el = document.getElementById('items');
    var sortable = Sortable.create(el,{
      store: {
    		/**
    		 * Get the order of elements. Called once during initialization.
    		 * @param   {Sortable}  sortable
    		 * @returns {Array}
    		 */
    		get: function (sortable) {
    			var order = localStorage.getItem(sortable.options.group.name);
    			return order ? order.split('|') : [];
    		},

    		/**
    		 * Save the order of elements. Called onEnd (when the item is dropped).
    		 * @param {Sortable}  sortable
    		 */
    		set: function (sortable) {
    			var order = sortable.toArray();
    			console.log(order);
    		}
    	}
    });
  }
};
