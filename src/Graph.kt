// Simple graph class written in order to learn about graph traversals and sorting
// Graph is directed
// Assumption - labels are unique

class Graph(elements: ArrayList<String> = ArrayList(0)) {
    private var numberOfElements = elements.size
    private var graphList = ArrayList<ArrayList<Vertex>>(numberOfElements)
    private var vertexList = ArrayList<Vertex>(numberOfElements)

    init {
        for(i in 0 until numberOfElements) {
            graphList.add(ArrayList(0))
        }

        for(i in 0 until numberOfElements) {
            vertexList.add(Vertex(elements[i]))
        }
    }

    fun addVertex(label: String) {
        val vertex = Vertex(label)
        graphList.add(ArrayList())
        vertexList.add(vertex)
        numberOfElements++
    }

    private fun findIndex(l: String): Int {
        for ((index, vertex) in vertexList.withIndex()) {
            if(vertex.label == l){
                return index
            }
        }
        return -1
    }

    fun addEdge(label1: String, label2: String) {
        val index = findIndex(label1)
        graphList[index].add(vertexList[findIndex(label2)])
    }

    private fun clear() {
        for(vertex in vertexList) {
            vertex.hasBeenVisited = false
        }
    }

    fun bfs(label: String) {
        val queue = ArrayDeque<Vertex>(numberOfElements)
        var workVertex: Vertex? = vertexList[findIndex(label)]
        workVertex!!.hasBeenVisited = true
        queue.addLast(workVertex)

        while(queue.isNotEmpty()) {
            for(ver in graphList[findIndex(workVertex!!.label)]) {
                if(!ver.hasBeenVisited) {
                    queue.addLast(ver)
                    ver.hasBeenVisited = true
                }
            }
            println(workVertex.label)
            queue.removeFirst()
            workVertex = queue.firstOrNull()
        }

        clear()
    }

    fun dfs(label: String) {
        var check: Boolean
        val stack = ArrayDeque<Vertex>(numberOfElements)
        var workIndex = findIndex(label)
        var workVertex: Vertex = vertexList[workIndex]
        stack.addLast(workVertex)

        while(stack.isNotEmpty()) {
            workVertex = stack.last()
            workIndex = findIndex(workVertex.label)
            check = true
            if(!workVertex.hasBeenVisited) {
                println(workVertex.label)
                workVertex.hasBeenVisited = true
            }
            for(element in graphList[workIndex]) {
                if(!element.hasBeenVisited) {
                    stack.addLast(element)
                    check = false
                    break
                }
            }

            if(check) {
                stack.removeLast()
            }
        }

        clear()
    }
}

fun main() {
    val gr = Graph()
    val v1 = "1"
    val v2 = "2"
    val v3 = "3"
    val v4 = "4"
    val v5 = "5"
    val v6 = "6"
    val v7 = "7"
    val v8 = "8"
    val v9 = "9"
    gr.addVertex(v1)
    gr.addVertex(v2)
    gr.addVertex(v3)
    gr.addVertex(v4)
    gr.addVertex(v5)
    gr.addVertex(v6)
    gr.addVertex(v7)
    gr.addVertex(v8)
    gr.addVertex(v9)
    gr.addEdge(v1, v2)
    gr.addEdge(v2, v3)
    gr.addEdge(v3, v4)
    gr.addEdge(v1, v5)
    gr.addEdge(v1, v6)
    gr.addEdge(v6, v7)
    gr.addEdge(v7, v8)
    gr.addEdge(v1, v9)
    gr.dfs("1")
    println()
    gr.bfs("1")
}