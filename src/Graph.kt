class Graph(var numberOfElements: Int, elements: ArrayList<String>) {
    var graphList = ArrayList<ArrayList<Vertex>>(numberOfElements)
    var vertexList = ArrayList<Vertex>(numberOfElements)

    init {
        for(i in 0 until numberOfElements) {
            graphList.add(ArrayList(0))
        }

        for(i in 0 until numberOfElements) {
            vertexList.add(Vertex(elements[i]))
        }
    }

    fun addVertex(vertex: Vertex) {
        graphList.add(ArrayList())
        vertexList.add(vertex)
        numberOfElements++
    }

    private fun findIndex(label: String): Int {
        for ((index, vertex) in vertexList.withIndex()) {
            if(vertex.label == label){
                return index
            }
        }
        return -1
    }

    fun addEdge(label1: String, label2: String) {
        val index = findIndex(label1)
        graphList[index].add(vertexList[findIndex(label2)])
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
    }
}

fun main() {
    val a = ArrayList<String>(2)
    val ver1 = "0"
    val ver2 = "1"
    val ver3 = "2"
    val ver4 = "3"
    val ver5 = "4"
    val ver6 = "5"
    a.add(ver1)
    a.add(ver2)
    a.add(ver3)
    a.add(ver4)
    a.add(ver5)
    a.add(ver6)
    val g = Graph(6, a)
    g.addEdge(ver1, ver2)
    g.addEdge(ver2, ver1)
    g.addEdge(ver1, ver3)
    g.addEdge(ver2, ver4)
    g.addEdge(ver3, ver4)
    g.addEdge(ver2, ver1)
    g.addEdge(ver4, ver5)
    g.addEdge(ver5, ver4)
    g.addEdge(ver5, ver3)
    g.addEdge(ver3, ver6)
    g.addEdge(ver6, ver5)
    g.bfs("0")
}