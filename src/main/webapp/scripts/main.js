function onExe(){
	var pid = document.getElementById('pid').value;
	var sd = document.getElementById('sd').value;
	var res = document.getElementById('results')
	fetch('./crud?pid='+pid+'&sd='+sd)
	  .then(response => response.json())
	  .then(data => {
	  	data.forEach((d)=>{
	  		var ele = document.createElement('tr');
			ele.innerHTML = `
			    <td>${d['product_id']}</td>
			    <td>${d['product_name']}</td>
			    <td>${d['category_id']}</td>
			    <td>${d['unit_price']}</td>
			  `;
			res.appendChild(ele);
	  	})
	  });
}