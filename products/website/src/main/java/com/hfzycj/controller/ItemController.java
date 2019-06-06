package com.hfzycj.controller;

import com.hfzycj.domain.Item;
import com.hfzycj.util.FileUtil;
import com.hfzycj.util.Pager;
import freemarker.template.TemplateException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Item Controller
 */
@RestController
@RequestMapping("manage/item")
public class ItemController extends BaseController {

    /**
     * List
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam(value = "p", required = true, defaultValue = "1") int p) {
        ModelAndView mav = new ModelAndView();
        Pager pager = new Pager();
        pager.setPageNo(p);
        int count = itemService.findAllCount(item, "");
        pager.setTotalCount(count);
        mav.addObject("list", itemService.findByPager(pager, "", "item_location, item_orderby"));
        mav.addObject("pager", pager);
        mav.addObject("count", count);
        mav.addObject("active", "Item");
        mav.setViewName("Item/itemList");
        return mav;
    }

    /**
     * Edit
     *
     * @param itemId
     * @return
     */
    @RequestMapping("edit/{itemId:[\\d]+}")
    public ModelAndView edit(@PathVariable(value = "itemId") int itemId) {
        if (0 == itemId) {
            item = new Item();
            item.setItem_id(itemId);
        } else {
            item = itemService.findById(itemId);
        }
        return new ModelAndView("Item/itemEdit", "Item", item);
    }

    /**
     * Save
     *
     * @param Item
     * @param redirectAttributes
     * @return
     * @throws TemplateException
     * @throws IOException
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("item") @Valid com.hfzycj.domain.Item Item, BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (0 == item.getItem_id()) {
            itemService.save(Item);
            redirectAttributes.addFlashAttribute("tips", "保存成功！");
        } else {
            itemService.update(Item);
            redirectAttributes.addFlashAttribute("tips", "修改成功！");
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("itemList", itemService.findAllList("", "itemOrderby", null));
        map.put("request", request);
        try {
            generateHTML(map, request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:/manage/Item");
    }

    /**
     * Generate HTML File
     *
     * @param map
     * @param request
     * @throws IOException
     * @throws TemplateException
     */
    protected void generateHTML(Map<String, Object> map, HttpServletRequest request) throws Exception {
        FileUtil.generateHTML("WEB-INF/ftl/site", "Item.ftl", "Item.html", map, request.getSession().getServletContext(), UPLOAD_PATH);
    }

}
