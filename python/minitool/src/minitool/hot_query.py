# coding=utf8

import urllib2
import re
import time

class BaiduHotQuery(object):
    """
    从百度搜索风云榜(http://top.baidu.com/)爬取搜索热词.
    """
    
    def __init__(self, http_timeout=10, fetch_interval_time=0.5, debug=False):
        """
        设置一些抓取页面的属性.

        :http_timeout:
            设置抓取页面的超时时间.
        :fetch_interval_time:
            设置每次抓取页面的时间间隔(秒). 抓取太频繁可能会发生网络错误.
        :debug:
            打开debug模式.
        """
        
        self.debug = debug
        self.http_timeout = http_timeout
        self.fetch_interval_time = fetch_interval_time
        pass
    
    def list_rss_links (self):
        html = urllib2.urlopen("http://top.baidu.com/rss.php", timeout=self.http_timeout).read()
        rss_table_match = re.search(re.compile('<table id="rss-cont" cellspacing="0">.*?var para_arr = \[(.*?)\].*?</script>', re.DOTALL), html)
        
        if rss_table_match:
            rss_table = rss_table_match.group(1)
            if rss_table:
                try:
                    rss_links = [ "http://top.baidu.com/%s" % (rss_item[1:len(rss_item) - 1]) for rss_item in rss_table.split(",")]
                    if len(rss_links) > 0:
                        return rss_links
                except:
                    pass
                
        raise IOError("The parser's rule is outdated.")
    
    def list_hot_query(self):
        hot_querys = []
        rss_links = self.list_rss_links();
        
        if self.debug:
            print "fetch %d links" % len(rss_links)
        
        for rss_link in rss_links:
            if self.debug:
                print "parse " + rss_link
            
            html = urllib2.urlopen(rss_link, timeout=self.http_timeout).read()
            hot_query_raw = re.findall(r'<a href="http://top.baidu.com/detail.php\?.*?".*?target="_blank">(.*?)</a>', html)
            
            if len(hot_query_raw) == 0:
                raise IOError("The parser's rule is outdated.")
            
            try:
                # 虽然页面的编码是gb2312，但是有部分词是gbk编码的，例如"囧"
                hot_querys += [ query.decode("gbk") for query in hot_query_raw ]
            except UnicodeDecodeError, e:
                raise IOError("%s\nDecode error when parsing %s." % (e, rss_link))
            
            time.sleep(self.fetch_interval_time)
            
        return set(hot_querys)
    
def main():
    bhq = BaiduHotQuery()
    hot_queries = bhq.list_hot_query()
    for query in hot_queries:
        print query
    print "hot query count: %d" % len(hot_queries)
    pass

if __name__ == '__main__':
    main();

