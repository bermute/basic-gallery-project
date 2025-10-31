package kr.co.wikibook.basicgallery.item.service;

import kr.co.wikibook.basicgallery.item.dto.ItemRead;
import kr.co.wikibook.basicgallery.item.entity.Item;
import kr.co.wikibook.basicgallery.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseItemService implements ItemService{

    private final ItemRepository itemRepository;

    @Override
    public List<ItemRead> findAll(){
        return itemRepository.findAll() //List<Item> 가져옴
                .stream() //Stream<Item> 으로 변환
                .map(Item::toRead) // Item -> ItemRead로 변환
                .toList(); // Stream<ItemRead> -> List<ItemRead> 로 수집
    }

    @Override
    public List<ItemRead> findAll(List<Integer> ids) {
        return itemRepository.findAllById(ids)
                .stream()
                .map(Item::toRead)
                .toList();
    }
}
