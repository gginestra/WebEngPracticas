var URI = "ws://localhost:8025/websockets/todo";
var socket; 

$( document).ready(function(){
	deleteButton();
	addButton();
	connectServer();
});


function connectServer(){
	socket = new WebSocket(URI);
	socket.onmessage = function(respuesta){
		toObject(respuesta.data);
	}
}

function addButton(){
	$("#addButton").click(function(){
		$('#addTodo').find('p').remove();
		if(!paramsIsEmpty()){
		var item = toJSON();
		socket.send("add-"+item);
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
			socket.send("delete-" + by + "-" + keyword);
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
	alert(json)
	var obj = JSON.parse(json);
	var tasks = obj.toDoList;
	for( var i in tasks){
		updateTable(tasks[i]);
	}

}

function updateTable(item){
	var row = $('<tr><td>' + item.task + '</td><td>' + item.project + '</td><td>' + item.context + '</td><td>' + item.priority +'</td></tr>');
	$('#listToDo').find('tbody').append(row);
}





