# tech-keywords-crawler

## Synopsis
This is a `crawler` program based on `Java Jsoup` and `Java Multi-Threading` using `ExecutorService`. 

The program crawls a `job boarding` website. It collects keywords from all job posts (around 1,000 posts) based on user defined searching criteria. 

### Example 
In default, the crawler targets at `software engineer` job posts. It counts the occurrence of keywords from the following list: 

{"java", "c", "javascript", "node", "c++", "python", "html", "css", "angular", "bootstrap", "react", "php", "vue", "object-oriented", "spring", "mvc", "mysql", "sql", "tcp/ip", "c#", "ruby", ".net", "rest"}

For further development, more options can be added into the list.

## Design
The program is completely written in Java. 
### Crawler
The program uses `Jsoup` to crawl the web pages and parse the html elements.

### Multithreading
For better performance, the program utilizes multithreading to assign tasks. This is achieved by using `ExecutorService` and `Runnable Interface`. 