// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: GameMain2.proto

package com.ma.test.protobuf;

public final class GameMain2 {
  private GameMain2() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_gamedata2_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_gamedata2_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_gamedata2_gamedata1_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_gamedata2_gamedata1_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017GameMain2.proto\"K\n\tgamedata2\022#\n\005datas\030" +
      "\001 \003(\0132\024.gamedata2.gamedata1\032\031\n\tgamedata1" +
      "\022\014\n\004data\030\001 \003(\005B\030\n\024com.ma.test.protobufP\001" +
      "b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_gamedata2_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_gamedata2_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_gamedata2_descriptor,
        new java.lang.String[] { "Datas", });
    internal_static_gamedata2_gamedata1_descriptor =
      internal_static_gamedata2_descriptor.getNestedTypes().get(0);
    internal_static_gamedata2_gamedata1_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_gamedata2_gamedata1_descriptor,
        new java.lang.String[] { "Data", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}