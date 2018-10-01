#!/usr/bin/env bash
java --add-opens java.base/java.lang=spring.core --module-path=target/modules --module reactive.spring.boot