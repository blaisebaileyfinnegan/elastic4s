package com.sksamuel.elastic4s.requests.searches.aggs

import com.sksamuel.elastic4s.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s.requests.script.ScriptBuilderFn

object AvgAggregationBuilder {
  def apply(agg: AvgAggregation): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.startObject("avg")
    agg.field.foreach(builder.field("field", _))
    agg.missing.foreach(builder.autofield("missing", _))
    agg.script.foreach { script =>
      builder.rawField("script", ScriptBuilderFn(script))
    }
    builder.endObject()
    builder.endObject()
  }
}
