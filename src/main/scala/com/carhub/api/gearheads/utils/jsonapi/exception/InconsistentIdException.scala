package com.carhub.api.gearheads.utils.jsonapi.exception

import java.io.Closeable

import com.fasterxml.jackson.databind.JsonMappingException

class InconsistentIdException(processor: Closeable) extends JsonMappingException(processor, "Ids are inconsistent.")
