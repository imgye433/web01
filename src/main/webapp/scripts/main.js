function onExe(n){
	var pid = document.getElementById('pid'+n).value;
	var res = document.getElementById('results')
	fetch('./crud?pid='+pid+'&sd='+n)
	  .then(response => response.json())
	  .then(data => {
	  	if(n===1){
	  	data.forEach((d)=>{
	  		var ele = document.createElement('tr');
			ele.innerHTML = `
			    <td>${d['product_id']}</td>
			    <td>${d['product_name']}</td>
			    <td>${d['category_id']}</td>
			    <td>${d['unit_price']}</td>
			  `;
			res.appendChild(ele);
	  	})}
	  	else{
	  		console.log(data);
	  	}
	  });
}