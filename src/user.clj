(require
 '[nextjournal.clerk :as clerk])

 (clerk/serve! {:browse? true :watch-paths ["notebooks"]})
 (clerk/clear-cache!)
 (clerk/show! "notebooks/cache-issue.clj")
