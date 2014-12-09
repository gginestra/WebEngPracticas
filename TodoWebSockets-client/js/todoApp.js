var URI = "http://localhost:8080/todo";


function getList(){
	$.ajax({
		url: URI,
		type: 'GET',
		contentType: 'application/json',
		success: function(json){
			toObject(json);
		}
	});

}

function deleteTask(by,keyword){
	$.ajax({
		url: URI + "/task/" + by + "/" + keyword,
		type: 'DELETE',
		success: function(){
			console.log("SUCCESS");
			$('#listToDo').find('tbody').find('tr').remove();
			getList();
		},
		error : function(error){
			var error =  "<p>ERROR: Task not found</p>";
			$('#deleteTodo').find('h4').append(error);
			$('#listToDo').find('tbody').find('tr').remove();
			getList();
		}
 
	});

}
function addTask(item){
	 $.ajax({
		url: URI,
		type: 'POST',
		contentType: 'application/json',
		dataType: "json",
		data: item,
		success: function(response, item){
			updateTable(response);
		}
	 
	 });

}

$( document).ready(function(){
	getList();
	addButton(); 
	deleteButton();
});

function addButton(){
	$("#addButton").click(function(){
		$('#addTodo').find('p').remove();
		if(!paramsIsEmpty()){
		var item = toJSON();
		addTask(item);
		$('#task').val('');
		$('#project').val('');
		$('#context').val('');
		$('#priority').val('');
		}else{
			var error =  "<p>ERROR: campos por rellenar</p>";
			$('#addTodo').find('h4').append(error);
		}
	});
}

function deleteButton(){
	$("#deleteButton").click(function(){
		$('#deleteTodo').find('p').remove();
		var keyword = $('#text').val();
		var i; 
		for (i=0;i<document.fby.by.length;i++){ 
			if (document.fby.by[i].checked) 
				break; 
		} 
		
		if(($('#text').val().length != 0) && (i<4)){
			var by = document.fby.by[i].value;
			console.log("selected radio:  " + by);
			deleteTask(by,keyword);
			document.fby.by[i].checked = null;
			$('#text').val('');
		}else{
			var error =  "<p>ERROR: campos por rellenar</p>";
			$('#deleteTodo').find('h4').append(error);
		}
		
	});
}

function paramsIsEmpty(){
	
return ( $('#task').val().length == 0 ) ||  ( $('#project').val().length == 0 ) || 
		( $('#context').val().length == 0 ) ||  ( $('#priority').val().length == 0 );
}

function toJSON(){
	return JSON.stringify({
				task: $(task).val(),
				project: $(project).val(),
				context: $(context).val(),
				priority: $(priority).val()
			});

}

function toObject(json){
	var tasks = json.toDoList;
	
	for( var i in tasks){
		updateTable(tasks[i]);
	}

}

function updateTable(item){
	var row = $('<tr><td>' + item.task + '</td><td>' + item.project + '</td><td>' + item.context + '</td><td>' + item.priority +'</td></tr>');
	$('#listToDo').find('tbody').append(row);
}




