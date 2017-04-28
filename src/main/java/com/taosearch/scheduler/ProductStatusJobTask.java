package com.taosearch.scheduler;

/**
 * Created by MengWeiBo on 2017-04-01
 */

public class ProductStatusJobTask {

//    @Autowired
//    private IProductService productService;

    public void run() throws Exception {

        System.out.println("1");
//        Date now = new Date();
//        //结束 -- 》代付款
//        List<Product> endProducts = productService.getProductByStatus(ProductStatus.END);
//        if (CollectionUtils.isNotEmpty(endProducts)) {
//            for (Product product : endProducts) {
//                int hour = DateTimeUtility.minuteBetween(product.getUpdateStatusTime(), now) / 60;
//                if (hour >= 24) {
//                    productService.modifyProductStatus(product.getId(), product.getEmployee().getId(), product.getStatus(), ProductStatus.PAY_WAIT);
//                }
//            }
//        }
//
//        //即将结束 --》结束
//        List<Product> endApproachProducts = productService.getProductByStatus(ProductStatus.END_APPROACH);
//        if (CollectionUtils.isNotEmpty(endApproachProducts)) {
//            for (Product product : endApproachProducts) {
//                Date tomorrow = DateTimeUtility.addDays(DateTimeUtility.getMinTimeOfDay(product.getCouponEndTime()), 1);
//
//                if (now.after(tomorrow)){
//                    productService.modifyProductStatus(product.getId(), product.getEmployee().getId(), product.getStatus(), ProductStatus.END);
//                }
//            }
//        }
//
//        //即将结束
//        List<Product> promoteProducts = productService.getProductByStatus(ProductStatus.PROMOTE);
//        if (CollectionUtils.isNotEmpty(promoteProducts)) {
//            for (Product product : promoteProducts) {
//                int hour = DateTimeUtility.minuteBetween(now, product.getCouponEndTime()) / 60;
//
//                if (hour <= 24 && hour > 0) {
//                    productService.modifyProductStatus(product.getId(), product.getEmployee().getId(), product.getStatus(), ProductStatus.END_APPROACH);
//                }
//            }
//        }
//
//        //复审--》推广
//        List<Product> twoAuditProducts = productService.getProductByStatus(ProductStatus.TWO_AUDIT);
//        if (CollectionUtils.isNotEmpty(twoAuditProducts)) {
//            for (Product product : twoAuditProducts) {
//                int hour = DateTimeUtility.minuteBetween(product.getUpdateStatusTime(), now) / 60;
//
//                if (hour >= 24) {
//                    productService.modifyProductStatus(product.getId(), product.getEmployee().getId(), product.getStatus(), ProductStatus.PROMOTE);
//                }
//            }
//        }
    }
}
