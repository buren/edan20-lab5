#Lunds Tekniska Högskola (LTH) - Course: EDAN20 - Lab5/6

####High memory usage
The program requires quite a lot of memory. So if a "out of heap space" exception occurs increase the memory the program can use. (From command line or in your IDE.)

##Prerequisites:

###Paths
  Build path for > Weka 3.7.2
  Set (absolute) folder path where the following files are located:
    swedish_talbanken05_test.conll
    swedish_talbanken05_test_blind.conll
    swedish_talbanken05_train.conll
  in ```src/format/Constants.java```

  they can be found here:
    http://fileadmin.cs.lth.se/cs/Education/EDAN20/corpus/conllx/sv/swedish_talbanken05_train.conll
    http://fileadmin.cs.lth.se/cs/Education/EDAN20/corpus/conllx/sv/swedish_talbanken05_test_blind.conll
    http://fileadmin.cs.lth.se/cs/Education/EDAN20/corpus/conllx/sv/swedish_talbanken05_test.conll

###Example header (for simple4.arff):

@attribute top_pos_stack {nil, ROOT, ++, NN, EN, AV, AJ, IK, IP, PO, VV, AB, TP, PR, RO, ID, PN, IC, MN, UK, VN, QV, WV, IQ, SV, HV, IR, IG, AN, MV, IM, BV, KV, GV, FV, PU, SP, XX, I?, IS, IT, IU, YY}
@attribute second_pos_stack {nil, ROOT, ++, NN, EN, AV, AJ, IK, IP, PO, VV, AB, TP, PR, RO, ID, PN, IC, MN, UK, VN, QV, WV, IQ, SV, HV, IR, IG, AN, MV, IM, BV, KV, GV, FV, PU, SP, XX, I?, IS, IT, IU, YY}
@attribute first_pos_queue {nil, ROOT, ++, NN, EN, AV, AJ, IK, IP, PO, VV, AB, TP, PR, RO, ID, PN, IC, MN, UK, VN, QV, WV, IQ, SV, HV, IR, IG, AN, MV, IM, BV, KV, GV, FV, PU, SP, XX, I?, IS, IT, IU, YY}
@attribute second_pos_queue {nil, ROOT, ++, NN, EN, AV, AJ, IK, IP, PO, VV, AB, TP, PR, RO, ID, PN, IC, MN, UK, VN, QV, WV, IQ, SV, HV, IR, IG, AN, MV, IM, BV, KV, GV, FV, PU, SP, XX, I?, IS, IT, IU, YY}
@attribute can_do_leftarc {true, false}
@attribute can_reduce {true, false}
@attribute action {la, ra, re, sh, la.++, la.+A, la.+F, la.AA, la.AG, la.AN, la.AT, la.C+, la.CA, la.DT, la.ES, la.ET, la.FO, la.FS, la.FV, la.IC, la.IG, la.IK, la.IM, la.IP, la.IQ, la.IR, la.IS, la.IT, la.IV, la.JC, la.JG, la.JR, la.JT, la.KA, la.MA, la.MS, la.NA, la.OA, la.OO, la.PA, la.PL, la.PT, la.RA, la.SP, la.SS, la.TA, la.UK, la.VA, la.XA, la.XF, la.XT, la.XX, ra.++, ra.+A, ra.+F, ra.AA, ra.AG, ra.AN, ra.AT, ra.BS, ra.C+, ra.CA, ra.CC, ra.CJ, ra.DB, ra.DT, ra.EF, ra.EO, ra.ES, ra.ET, ra.FO, ra.FS, ra.FV, ra.HD, ra.I?, ra.IC, ra.IG, ra.IK, ra.IM, ra.IO, ra.IP, ra.IQ, ra.IR, ra.IS, ra.IT, ra.IU, ra.IV, ra.JC, ra.JG, ra.JR, ra.JT, ra.KA, ra.MA, ra.MD, ra.MS, ra.NA, ra.OA, ra.OO, ra.PA, ra.PL, ra.PT, ra.RA, ra.ROOT, ra.SP, ra.SS, ra.ST, ra.TA, ra.UK, ra.VA, ra.VG, ra.VO, ra.VS, ra.XA, ra.XF, ra.XX}

@data


### Course lab5/6 links
http://cs.lth.se/english/course/edan20-language-technology/coursework/assignment-5-dependency-parsing/
http://cs.lth.se/english/course/edan20-language-technology/coursework/assignment-6-dependency-parsing-using-machine-learning-techniques/


## Test Result
perl eval.pl -g corpus/swedish_talbanken05_test.conll -s corpus/result_output.conll -q


## Results
4 arguments
  Labeled   attachment score: 3412 / 5021 * 100 = 67.95 %
  Unlabeled attachment score: 4029 / 5021 * 100 = 80.24 %
  Label accuracy score:       3547 / 5021 * 100 = 70.64 %

6 arguments
  Labeled   attachment score: 3548 / 5021 * 100 = 70.66 %
  Unlabeled attachment score: 4147 / 5021 * 100 = 82.59 %
  Label accuracy score:       3677 / 5021 * 100 = 73.23 %
