package com.codereviewer.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReviewResult {

  private final String filename;
  private final List<codeIssue> issues = new ArrayList<>();

  public ReviewResult(String filename){
    this.filename = filename;
  
  }
  public void addIssue(codeIssue issue){
    issues.add(issue);
  }
  
  public String getFilename(){
    return filename;
  }
  public List<codeIssue> getIssues(){
    return Collections.unmodifiableList(issues);
  }
}
