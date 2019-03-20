package apps.ricasares.com.data.entity.mapper

import apps.ricasares.com.data.entity.Children
import apps.ricasares.com.data.entity.ChildrenData

class ChildrenMapper : Mapper<List<ChildrenData>, List<Children>> {
    override fun map(source: List<ChildrenData>): List<Children> {
        return source.let {
            it.map {
                childrenData: ChildrenData ->  Children(childrenData)
            }
        }
    }
}