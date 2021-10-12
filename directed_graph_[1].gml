graph [
	comment "directed graph"
	id 1
	node [
		id 1
		label "node 0"
	]
	node [
		id 2
		label "node 1"
	]
	node [
		id 3
		label "node 2"
	]
	node [
		id 4
		label "node 3"
	]
	node [
		id 5
		label "node 4"
	]
	node [
		id 6
		label "node 5"
	]
	node [
		id 7
		label "node 6"
	]
	edge [
		source 0
		target 1
		label "Edge from node 0 to node 1"
	]
	edge [
		source 0
		target 2
		label "Edge from node 0 to node 2"
	]
	edge [
		source 1
		target 3
		label "Edge from node 1 to node 3"
	]
	edge [
		source 2
		target 3
		label "Edge from node 2 to node 3"
	]
	edge [
		source 2
		target 2
		label "Edge from node 2 to node 2"
	]
	edge [
		source 3
		target 4
		label "Edge from node 3 to node 4"
	]
	edge [
		source 3
		target 1
		label "Edge from node 3 to node 1"
	]
	edge [
		source 4
		target 5
		label "Edge from node 4 to node 5"
	]
	edge [
		source 4
		target 6
		label "Edge from node 4 to node 6"
	]
	edge [
		source 5
		target 3
		label "Edge from node 5 to node 3"
	]
]